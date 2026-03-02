package com.arthurtokarev.bankmanagement.controller;

import com.arthurtokarev.bankmanagement.entity.User;
import com.arthurtokarev.bankmanagement.entity.UserProfile;
import com.arthurtokarev.bankmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,
                                              @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/buildings/{buildingId}")
    public ResponseEntity<Void> assignUserToBuilding(@PathVariable Long userId,
                                                     @PathVariable Long buildingId) {
        userService.assignUserToBuilding(userId, buildingId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}/buildings/{buildingId}")
    public ResponseEntity<Void> removeUserFromBuilding(@PathVariable Long userId,
                                                       @PathVariable Long buildingId) {
        userService.removeUsersFromBuilding(userId, buildingId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/profile")
    public ResponseEntity<UserProfile> getUserProfile(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserProfile(id));
    }
}