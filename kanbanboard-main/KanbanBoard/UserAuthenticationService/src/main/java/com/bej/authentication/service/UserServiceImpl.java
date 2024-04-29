package com.bej.authentication.service;

import com.bej.authentication.domain.User;
import com.bej.authentication.exception.UserAlreadyExistsException;
import com.bej.authentication.exception.InvalidCredentialsException;
import com.bej.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

// Autowire the UserRepository using constructor autowiring
    private UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        //save the user in the db
        if (userRepository.findById(user.getUserId()).isPresent())
        {
            throw new UserAlreadyExistsException();
        }
        System.out.println(user);
        return userRepository.save(user);
    }

    @Override
    public User getUserByUserIdAndPassword(String userId, String password) throws InvalidCredentialsException {
      // Validate for wrong credentials
        User loggedInUser = userRepository.findByUserIdAndPassword(userId, password);
        if (loggedInUser == null) {
            throw new InvalidCredentialsException();
        }
        return loggedInUser;
    }

}
