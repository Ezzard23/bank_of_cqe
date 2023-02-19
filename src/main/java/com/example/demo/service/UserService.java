package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
    
    
    @Autowired
    private UserRepository repository;
    //CRUD

    public User createUsers(User user){
        user.setIdNum(UUID.randomUUID().toString().split("-")[0]);
        return repository.save(user);
    }

    public List<User> findAllUsers(){
        return repository.findAll();
    }

    public User getUserByUserId(String userId){
        return repository.findById(userId).get();
    }

    public String deleteUserByUserId(String UserId){
         try {
            repository.deleteById(UserId);
         }catch(Exception e){
            System.out.println(e);
            return e.getMessage();
         }
         return UserId;
    }
}
