package com.testapi.testapi.service;

import com.testapi.testapi.model.User;
import com.testapi.testapi.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User addUser(User user){
        return userRepo.save(user);
    }

    public List<User> findAll(){
        return userRepo.findAll();
    }

    public User findUserById(Long userId){
        return userRepo.findById(userId).get();
    }

    public void removeUser(Long userId){
        this.userRepo.deleteById(userId);
    }
}
