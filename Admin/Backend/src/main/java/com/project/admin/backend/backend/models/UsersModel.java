package com.project.admin.backend.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Users")
public class UsersModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    @JsonProperty("user_id")
    private Long userId;
    
    @Column(name = "college_id")
    @JsonProperty("college_id")
    private Long collegeId;
    
    @Column(name = "user_name")
    @JsonProperty("user_name")
    private String userName;
    
    @Column(name = "email_id")
    @JsonProperty("email_id")
    private String emailId;
    
    @ManyToOne
    @JoinColumn(name = "college_id", insertable = false, updatable = false)
    private CollegesModel college;
    


}
