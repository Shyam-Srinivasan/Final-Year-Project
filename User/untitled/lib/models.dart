import 'dart:convert';

import 'package:flutter/material.dart';

class DaySchedule {
  DaySchedule({
    required this.tasks,
    required this.hour,
    required this.minute,
    required this.enabled,
  });

  List<String> tasks;
  int hour;
  int minute;
  bool enabled;

  TimeOfDay get timeOfDay => TimeOfDay(hour: hour, minute: minute);

  DaySchedule copyWith({
    List<String>? tasks,
    int? hour,
    int? minute,
    bool? enabled,
  }) {
    return DaySchedule(
      tasks: tasks ?? List<String>.from(this.tasks),
      hour: hour ?? this.hour,
      minute: minute ?? this.minute,
      enabled: enabled ?? this.enabled,
    );
  }

  Map<String, dynamic> toJson() => {
        'tasks': tasks,
        'hour': hour,
        'minute': minute,
        'enabled': enabled,
      };

  static DaySchedule fromJson(Map<String, dynamic> json) {
    return DaySchedule(
      tasks: (json['tasks'] as List).map((e) => e.toString()).toList(),
      hour: json['hour'] as int,
      minute: json['minute'] as int,
      enabled: json['enabled'] as bool,
    );
  }

  static String encodeWeekly(Map<int, DaySchedule> weekly) {
    final map = weekly.map(
      (key, value) => MapEntry(key.toString(), value.toJson()),
    );
    return jsonEncode(map);
  }

  static Map<int, DaySchedule> decodeWeekly(String source) {
    final decoded = jsonDecode(source) as Map<String, dynamic>;
    return decoded.map(
      (key, value) => MapEntry(int.parse(key), DaySchedule.fromJson(value)),
    );
  }

  static String weekdayLabel(int weekday) {
    switch (weekday) {
      case DateTime.monday:
        return 'Monday';
      case DateTime.tuesday:
        return 'Tuesday';
      case DateTime.wednesday:
        return 'Wednesday';
      case DateTime.thursday:
        return 'Thursday';
      case DateTime.friday:
        return 'Friday';
      case DateTime.saturday:
        return 'Saturday';
      case DateTime.sunday:
        return 'Sunday';
      default:
        return 'Day';
    }
  }

  static Map<int, DaySchedule> defaultWeekly() {
    const defaultHour = 7;
    const defaultMinute = 0;
    return <int, DaySchedule>{
      DateTime.monday: DaySchedule(
        tasks: ['Workout', 'College work', 'Coding'],
        hour: defaultHour,
        minute: defaultMinute,
        enabled: true,
      ),
      DateTime.tuesday: DaySchedule(
        tasks: ['Workout', 'College work', 'Aptitude'],
        hour: defaultHour,
        minute: defaultMinute,
        enabled: true,
      ),
      DateTime.wednesday: DaySchedule(
        tasks: ['Workout', 'College work', 'Communication'],
        hour: defaultHour,
        minute: defaultMinute,
        enabled: true,
      ),
      DateTime.thursday: DaySchedule(
        tasks: ['Workout', 'College work', 'Coding'],
        hour: defaultHour,
        minute: defaultMinute,
        enabled: true,
      ),
      DateTime.friday: DaySchedule(
        tasks: ['Workout', 'College Work', 'Aptitude'],
        hour: defaultHour,
        minute: defaultMinute,
        enabled: true,
      ),
      DateTime.saturday: DaySchedule(
        tasks: ['Workout', 'Project'],
        hour: defaultHour,
        minute: defaultMinute,
        enabled: true,
      ),
      DateTime.sunday: DaySchedule(
        tasks: ['Workout', 'Project'],
        hour: defaultHour,
        minute: defaultMinute,
        enabled: true,
      ),
    };
  }
}
