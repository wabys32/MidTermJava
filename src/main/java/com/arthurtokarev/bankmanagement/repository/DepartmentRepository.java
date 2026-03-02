package com.arthurtokarev.bankmanagement.repository;

import com.arthurtokarev.bankmanagement.entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Building, Long> {
}