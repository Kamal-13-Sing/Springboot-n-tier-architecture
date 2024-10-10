package com.bookStore.bookStoreManagement.service;

import com.bookStore.bookStoreManagement.model.User;
import com.bookStore.bookStoreManagement.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements  UserService{

    private  final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // register new user

    public boolean registerUser(User userObj){

        boolean status = false;

        User userSaveObj = new User(
                userObj.getUserId(),
                userObj.getFullname(),
                userObj.getUsername(),
                userObj.getPassword(),
                userObj.getContact(),
                true, true, true
        );

        User userRegistered =  userRepository.save(userSaveObj);

        if(userRegistered != null){
            status = true;
        }

         return  status;

    }
}
