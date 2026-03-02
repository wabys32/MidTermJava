package com.arthurtokarev.bankmanagement.repository;

import com.arthurtokarev.bankmanagement.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<Department, Long> {
}