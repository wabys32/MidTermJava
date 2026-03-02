package com.arthurtokarev.bankmanagement.service;

import com.arthurtokarev.bankmanagement.entity.Building;
import com.arthurtokarev.bankmanagement.entity.Department;
import com.arthurtokarev.bankmanagement.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BuildingService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional
    public Building createBuilding(Building building) {
        if (building.getDepartments() != null) {
            building.getDepartments().forEach(department -> department.setBuilding(building));  // Bidirectional sync
        }
        return departmentRepository.save(building);
    }

    @Transactional
    public Building addDepartmentsToBuilding(Long buildingId, List<Department> departments) {
        Building building = departmentRepository.findById(buildingId).orElseThrow(() -> new RuntimeException("Building not found"));
        departments.forEach(department -> {
            department.setBuilding(building);
            building.getDepartments().add(department);
        });
        return departmentRepository.save(building);
    }

    public Optional<Building> getBuildingById(Long id) {
        return departmentRepository.findById(id);
    }
}