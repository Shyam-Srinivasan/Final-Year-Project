package com.project.admin.backend.backend.models;


import com.fasterxml.jackson.annotation.JsonProperty;
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
        name = "Colleges"
        /*,uniqueConstraints = {
                @UniqueConstraint(name = "uk_colleges_college_id", columnNames = "college_id"),
                @UniqueConstraint(name = "uk_colleges_college_name", columnNames = "college_name"),
                @UniqueConstraint(name = "uk_colleges_college_domain_address", columnNames = "domain_address"),
                @UniqueConstraint(name = "uk_colleges_college_address", columnNames = "address"),
                @UniqueConstraint(name = "uk_colleges_college_email_address", columnNames = "email_address"),
                @UniqueConstraint(name = "uk_colleges_college_contact_no", columnNames = "contact_no")

        }*/
)
public class CollegesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "college_id", nullable = false, unique = true, length = 36)
    @JsonProperty("college_id")
    private String collegeId;

    @Column(name = "college_name", nullable = false, unique = true)
    @JsonProperty("name")
    private String name;

    @Column(name = "address", nullable = false, length = 512)
    @JsonProperty("address")
    private String address;

    @Column(name = "domain_address", nullable = false, unique = true)
    @JsonProperty("domain_address")
    private String domain_address;

    @Column(name = "email_address", nullable = false, unique = true)
    @JsonProperty("email")
    private String email;

    @Column(name = "contact_no", nullable = false, unique = true, length = 32)
    @JsonProperty("contact_no")
    private String contactNo;
}