package com.tinylms.service;

import com.tinylms.domain.User;
import com.tinylms.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public boolean addUser(User user) {
        if (userRepo.exists(user.getUsername())) {
            return false;
        }
        User resp = userRepo.insert(user);
        return resp != null;
    }

    public boolean updateUser(User user) {
        if (!userRepo.exists(user.getUsername())) {
            return false;
        }
        User resp = userRepo.save(user);
        return resp != null;
    }

    public boolean deleteUser(String username) {
        if (!userRepo.exists(username)) {
            return false;
        }
        userRepo.delete(username);
        return true;
    }

    public User getUser(String username) {
        return userRepo.findOne(username);
    }

    public User save(User user) {
        return userRepo.save(user);
    }
}
