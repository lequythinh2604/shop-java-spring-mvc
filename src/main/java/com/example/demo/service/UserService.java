package com.example.demo.service;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import jakarta.validation.constraints.Email;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User handleSaveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> handleFindAllUsers() {
        return userRepository.findAll();
    }

    public User handleFindUserById(Long id) {
        return userRepository.findOneById(id);
    }

    public void handleDeleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Role getRoleByName(String roleName) {
        return roleRepository.findByName(roleName);
    }

    public boolean checkEmailExist(String email) {
        return userRepository.existsByEmail(email);
    }

    public User getUserByEmail(String username) {
        return userRepository.findByEmail(username);
    }
}
