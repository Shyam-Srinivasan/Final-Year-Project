import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

import 'models.dart';
import 'notification_service.dart';

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await NotificationService.instance.init();
  runApp(const ScheduleApp());
}

class ScheduleApp extends StatelessWidget {
  const ScheduleApp({super.key});

  @override
  Widget build(BuildContext context) {
    final colorScheme = ColorScheme.fromSeed(seedColor: Colors.indigo);
    return MaterialApp(
      title: 'Smart Week Planner',
      theme: ThemeData(
        colorScheme: colorScheme,
        useMaterial3: true,
        appBarTheme: AppBarTheme(
          backgroundColor: colorScheme.surface,
          foregroundColor: colorScheme.onSurface,
          elevation: 0,
        ),
          cardTheme: CardThemeData(
            color: colorScheme.surface,
            surfaceTintColor: colorScheme.surfaceTint,
            elevation: 2,
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(16),
            ),
          ),
        chipTheme: ChipThemeData(
          shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
          selectedColor: colorScheme.primaryContainer,
        ),
      ),
      home: const HomeScreen(),
    );
  }
}

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> with TickerProviderStateMixin {
  late final TabController _tabController;
  final List<int> _weekdays = [
    DateTime.monday,
    DateTime.tuesday,
    DateTime.wednesday,
    DateTime.thursday,
    DateTime.friday,
    DateTime.saturday,
    DateTime.sunday,
  ];

  Map<int, DaySchedule> _weekly = DaySchedule.defaultWeekly();
  bool _loading = true;
  final TextEditingController _taskController = TextEditingController();

  @override
  void initState() {
    super.initState();
    _tabController = TabController(length: 7, vsync: this);
    _load();
  }

  Future<void> _load() async {
    final prefs = await SharedPreferences.getInstance();
    final saved = prefs.getString('weekly_schedule');
    if (saved != null) {
      setState(() {
        _weekly = DaySchedule.decodeWeekly(saved);
      });
    }
    setState(() => _loading = false);
    // After load, ensure notifications are scheduled according to current state
    await _rescheduleAll();
  }

  Future<void> _persist() async {
    final prefs = await SharedPreferences.getInstance();
    await prefs.setString('weekly_schedule', DaySchedule.encodeWeekly(_weekly));
  }

  Future<void> _rescheduleAll() async {
    // Schedule/cancel per day using notification IDs 1..7
    for (final day in _weekdays) {
      final ds = _weekly[day]!;
      if (ds.enabled && ds.tasks.isNotEmpty) {
        final body = ds.tasks.join(' • ');
        await NotificationService.instance.scheduleWeekly(
          id: day,
          title: 'Schedule for ${DaySchedule.weekdayLabel(day)}',
          body: body,
          weekday: day,
          hour: ds.hour,
          minute: ds.minute,
        );
      } else {
        await NotificationService.instance.cancel(day);
      }
    }
  }

  void _addTask(int day) {
    final text = _taskController.text.trim();
    if (text.isEmpty) return;
    setState(() {
      final current = _weekly[day]!.tasks;
      if (!current.contains(text)) {
        current.add(text);
      }
      _weekly[day] = _weekly[day]!.copyWith(tasks: current);
      _taskController.clear();
    });
    _persist();
    _rescheduleAll();
  }

  void _removeTask(int day, String task) {
    setState(() {
      final current = _weekly[day]!.tasks;
      current.remove(task);
      _weekly[day] = _weekly[day]!.copyWith(tasks: current);
    });
    _persist();
    _rescheduleAll();
  }

  Future<void> _pickTime(int day) async {
    final current = _weekly[day]!;
    final picked = await showTimePicker(
      context: context,
      initialTime: current.timeOfDay,
      helpText: 'Pick reminder time',
    );
    if (picked != null) {
      setState(() {
        _weekly[day] =
            current.copyWith(hour: picked.hour, minute: picked.minute);
      });
      _persist();
      _rescheduleAll();
    }
  }

  @override
  void dispose() {
    _taskController.dispose();
    _tabController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final cs = Theme.of(context).colorScheme;
    return Scaffold(
      extendBodyBehindAppBar: true,
      appBar: AppBar(
        title: const Text('Smart Week Planner'),
        bottom: PreferredSize(
          preferredSize: const Size.fromHeight(64),
          child: _buildTabs(cs),
        ),
      ),
      body: Container(
        decoration: BoxDecoration(
          gradient: LinearGradient(
            begin: Alignment.topLeft,
            end: Alignment.bottomRight,
            colors: [cs.primaryContainer, cs.surface],
          ),
        ),
        child: _loading
            ? const Center(child: CircularProgressIndicator())
            : TabBarView(
                controller: _tabController,
                children: _weekdays.map((d) => _buildDayView(context, d)).toList(),
              ),
      ),
    );
  }

  Widget _buildTabs(ColorScheme cs) {
    return Container(
      margin: const EdgeInsets.fromLTRB(12, 0, 12, 12),
      padding: const EdgeInsets.all(6),
      decoration: BoxDecoration(
        color: cs.surface.withOpacity(0.8),
        borderRadius: BorderRadius.circular(16),
      ),
      child: TabBar(
        controller: _tabController,
        isScrollable: true,
        indicator: BoxDecoration(
          color: cs.primary.withOpacity(0.12),
          borderRadius: BorderRadius.circular(12),
        ),
        labelColor: cs.primary,
        unselectedLabelColor: cs.onSurface,
        tabs: _weekdays
            .map((d) => Tab(text: DaySchedule.weekdayLabel(d)))
            .toList(),
      ),
    );
  }

  Widget _buildDayView(BuildContext context, int day) {
    final ds = _weekly[day]!;
    final cs = Theme.of(context).colorScheme;
    return SingleChildScrollView(
      padding: const EdgeInsets.fromLTRB(16, 120, 16, 24),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          Card(
            child: Padding(
              padding: const EdgeInsets.all(16.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Row(
                    children: [
                      Icon(Icons.event_note, color: cs.primary),
                      const SizedBox(width: 8),
                      Text(
                        'Tasks for ${DaySchedule.weekdayLabel(day)}',
                        style: Theme.of(context).textTheme.titleLarge,
                      ),
                    ],
                  ),
                  const SizedBox(height: 12),
                  Wrap(
                    spacing: 8,
                    runSpacing: 8,
                    children: ds.tasks.isEmpty
                        ? [
                            Text(
                              'No tasks yet. Add some below!',
                              style: TextStyle(color: cs.onSurfaceVariant),
                            )
                          ]
                        : ds.tasks
                            .map((t) => InputChip(
                                  label: Text(t),
                                  onDeleted: () => _removeTask(day, t),
                                ))
                            .toList(),
                  ),
                  const SizedBox(height: 12),
                  Row(
                    children: [
                      Expanded(
                        child: TextField(
                          controller: _taskController,
                          decoration: const InputDecoration(
                            hintText: 'Add a task (e.g., Workout)',
                            border: OutlineInputBorder(),
                          ),
                          onSubmitted: (_) => _addTask(day),
                        ),
                      ),
                      const SizedBox(width: 8),
                      FilledButton.icon(
                        onPressed: () => _addTask(day),
                        icon: const Icon(Icons.add),
                        label: const Text('Add'),
                      ),
                    ],
                  ),
                ],
              ),
            ),
          ),
          const SizedBox(height: 16),
          Card(
            child: Padding(
              padding: const EdgeInsets.symmetric(horizontal: 8.0),
              child: SwitchListTile(
                secondary: Icon(
                  ds.enabled ? Icons.notifications_active : Icons.notifications_off,
                  color: ds.enabled ? cs.primary : cs.onSurfaceVariant,
                ),
                title: const Text('Enable Reminder'),
                subtitle: const Text('Receive a weekly reminder at your chosen time'),
                value: ds.enabled,
                onChanged: (val) {
                  setState(() {
                    _weekly[day] = ds.copyWith(enabled: val);
                  });
                  _persist();
                  _rescheduleAll();
                },
              ),
            ),
          ),
          const SizedBox(height: 16),
          Card(
            child: ListTile(
              leading: Icon(Icons.access_time, color: cs.primary),
              title: const Text('Reminder time'),
              subtitle: Text(_formatTime(ds.timeOfDay)),
              trailing: FilledButton(
                onPressed: () => _pickTime(day),
                child: const Text('Change'),
              ),
            ),
          ),
          const SizedBox(height: 8),
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 4.0),
            child: Text(
              'Notifications repeat every week on ${DaySchedule.weekdayLabel(day)} at the selected time.',
              style: TextStyle(color: cs.onSurfaceVariant),
              textAlign: TextAlign.center,
            ),
          ),
        ],
      ),
    );
  }

  String _formatTime(TimeOfDay tod) {
    final h = tod.hourOfPeriod == 0 ? 12 : tod.hourOfPeriod;
    final mm = tod.minute.toString().padLeft(2, '0');
    final period = tod.period == DayPeriod.am ? 'AM' : 'PM';
    return '$h:$mm $period';
  }
}
