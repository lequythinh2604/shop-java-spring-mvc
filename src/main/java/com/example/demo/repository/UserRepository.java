package com.example.demo.repository;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByEmail(String email);

    User findOneById(Long id);

    boolean existsByEmail(String email);

    User findByEmail(String username);
}
