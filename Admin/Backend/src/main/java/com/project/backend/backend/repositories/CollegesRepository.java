package com.project.backend.backend.repositories;

import com.project.backend.backend.models.CollegesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollegesRepository extends JpaRepository<CollegesModel, Long> {}