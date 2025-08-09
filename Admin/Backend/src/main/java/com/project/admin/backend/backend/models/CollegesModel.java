package com.project.admin.backend.backend.models;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "CollegesModel",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_colleges_college_id", columnNames = "college_id"),
                @UniqueConstraint(name = "uk_colleges_college_name", columnNames = "college_name"),
                @UniqueConstraint(name = "uk_colleges_college_address", columnNames = "address"),
                @UniqueConstraint(name = "uk_colleges_college_email", columnNames = "email"),
                @UniqueConstraint(name = "uk_colleges_college_phone_no", columnNames = "phone_no"),
                @UniqueConstraint(name = "uk_colleges_college_domain_address", columnNames = "domain_address"),

        }
)
public class CollegesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "college_id", nullable = false, unique = true, length = 64)
    private String college_id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "address", nullable = false, length = 512)
    private String address;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone_no", nullable = false, unique = true, length = 32)
    private String phone_no;

    @Column(name = "domain_address", nullable = false, unique = true)
    private String domain_address;
}