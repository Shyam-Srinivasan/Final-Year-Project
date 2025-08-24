package com.project.admin.backend.backend.repositories;

import com.project.admin.backend.backend.models.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UsersModel, Long> {
}
