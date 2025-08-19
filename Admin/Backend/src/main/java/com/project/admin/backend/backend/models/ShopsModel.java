package com.project.admin.backend.backend.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "Shops"
)
public class ShopsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id", nullable = false, unique = true, length = 36)
    @JsonProperty("shop_id")
    private Long shopId;
    
    @Column(name = "college_id", nullable = false, length = 36)
    @JsonProperty("college_id")
    private Long collegeId;
    
    @Column(name = "shop_name", nullable = false, unique = true)
    @JsonProperty("shop_name")
    private String shopName;
    
    @Column(name = "password", nullable = false, length = 255)
    @JsonProperty("password")
    private String password;
    
    @ManyToOne
    @JoinColumn(name = "college_id", insertable = false, updatable = false)
    @JsonProperty("college")
    private CollegesModel college;
    
}
