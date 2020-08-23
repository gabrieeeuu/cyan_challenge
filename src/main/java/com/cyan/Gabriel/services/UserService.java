package com.cyan.Gabriel.services;

import com.cyan.Gabriel.dao.UserDAO;
import com.cyan.Gabriel.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User saveUser(User user){

        // Validacoes
        if (userDAO.findByEmail(user.getEmail()) != null) {
            throw new InternalError("Email already registered.");
        }

        return userDAO.save(user);
    }

    public List<User> findAllUsers(){
        return userDAO.findAll();
    }

    public User findUserByEmail(String email){
        return userDAO.findByEmail(email);
    }
}
