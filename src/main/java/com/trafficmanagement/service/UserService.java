package com.trafficmanagement.service;

import com.trafficmanagement.dao.UserDAO;
import com.trafficmanagement.model.User;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public boolean registerUser(String idCard, String password) {
        return userDAO.registerUser(new User(idCard, password));
    }

    public User loginUser(String idCard, String password) {
        return userDAO.loginUser(idCard, password);
    }
}
