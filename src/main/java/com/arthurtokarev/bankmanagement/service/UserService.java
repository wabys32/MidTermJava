package com.arthurtokarev.bankmanagement.service;

import com.arthurtokarev.bankmanagement.entity.Building;
import com.arthurtokarev.bankmanagement.entity.User;
import com.arthurtokarev.bankmanagement.entity.UserProfile;
import com.arthurtokarev.bankmanagement.repository.DepartmentRepository;
import com.arthurtokarev.bankmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional
    public User createUser(User user) {
        if (user.getUserProfile() != null) {
            user.getUserProfile().setUser(user);
        }
        return userRepository.save(user);
    }
    @Transactional
    public void assignUserToBuilding(Long userId, Long buildingId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Building building = departmentRepository.findById(buildingId).orElseThrow(() -> new RuntimeException("Building not found"));
        user.getBuildings().add(building);
        building.getUsers().add(user);
        userRepository.save(user);
    }
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
    @Transactional
    public User updateUser(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setEmail(updatedUser.getEmail());
        // update profile safely
        if (updatedUser.getUserProfile() != null) {
            UserProfile profile = updatedUser.getUserProfile();
            profile.setUser(existingUser);
            existingUser.setUserProfile(profile);
        }
        return userRepository.save(existingUser);
    }

    @Transactional
    public void removeUsersFromBuilding(Long userId, Long buildingId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Building building = departmentRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Building not found"));

        user.getBuildings().remove(building);
        building.getUsers().remove(user);
    }

    @Transactional(readOnly = true)
    public UserProfile getUserProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getUserProfile();
    }

    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }
}

