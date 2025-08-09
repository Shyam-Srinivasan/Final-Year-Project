package com.project.admin.backend.backend.repositories;

import com.project.admin.backend.backend.models.CollegesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollegesRepository extends JpaRepository<CollegesModel, Long>{
    CollegesModel findByName(String College_name);
}