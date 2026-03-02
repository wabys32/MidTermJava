package com.arthurtokarev.bankmanagement.repository;

import com.arthurtokarev.bankmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}