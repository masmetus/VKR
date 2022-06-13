package com.example.VKR.service;

import com.example.VKR.dto.user.UserResponse;
import com.example.VKR.model.Room;
import com.example.VKR.model.User;
import com.example.VKR.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserResponse getDetails(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());
        userResponse.setName(user.getFirstName());
        userResponse.setSurname(user.getLastName());
        return userResponse;
    }

    @Transactional
    public boolean changeEmail(User user, String email) {
        user.setEmail(email);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean changeUsername(User user, String username) {
        user.setUsername(username);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean changeName(User user, String name) {
        user.setFirstName(name);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean changeLastName(User user, String surname) {
        user.setLastName(surname);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean deleteUser(Long id) {
        User user = userRepository.getById(id);
        try {
            userRepository.delete(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean changeUser(Long id, User user) {
        Optional<User> user1 = userRepository.findById(id);
        if (user1.isPresent()) {
            user1.get().setRoles(user.getRoles());
            //user1.get().setPassword(user.getPassword());
            user1.get().setFirstName(user.getFirstName());
            user1.get().setLastName(user.getLastName());
            user1.get().setUsername(user.getUsername());
            user1.get().setEmail(user.getEmail());
            user1.get().setPhoneNumber(user.getPhoneNumber());
            userRepository.save(user);
        } else {
            return false;
        }
        return true;

    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


}
