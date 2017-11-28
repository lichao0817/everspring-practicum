package service;

import dao.UserDao;
import domain.User;

import java.util.List;

/**
 * Created by Chao on 2017/11/28.
 */
public class UserService {

    private UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    public List<User> getAllUsers() {
        return null;
    }

    public boolean addUser(User user) {
        return false;
    }

    public boolean updateUser(User user) {
        return false;
    }

    public boolean deleteUser(User user) {
        return false;
    }

    public User getUser(String username) {
        return null;
    }
}
