package com.bej.authentication.controller;

import com.bej.authentication.exception.UserAlreadyExistsException;
import com.bej.authentication.exception.InvalidCredentialsException;
import com.bej.authentication.security.JWTSecurityTokenGeneratorImpl;
import com.bej.authentication.security.SecurityTokenGenerator;
import com.bej.authentication.security.JWTSecurityTokenGeneratorImpl;
import com.bej.authentication.service.IUserService;
import com.bej.authentication.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2")
public class UserController {


    private IUserService iUserService;
    private SecurityTokenGenerator securityTokenGenerator;

    //Autowire the dependencies for UserService and SecurityTokenGenerator
    @Autowired
    public UserController(IUserService iUserService, SecurityTokenGenerator securityTokenGenerator) {
        this.iUserService = iUserService;
        this.securityTokenGenerator = securityTokenGenerator;
    }


    // save user autocall by register user of kanban service
    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyExistsException {
        return new ResponseEntity<>(iUserService.saveUser(user), HttpStatus.CREATED);
    }

    // check the credential if correct then Generate the token on login
    @PostMapping("/login")

    public ResponseEntity<?> login(@RequestBody User user) throws InvalidCredentialsException {

        System.out.println(user.getUserId());
        System.out.println(user.getPassword());
        User retrievedUser = iUserService.getUserByUserIdAndPassword(user.getUserId(), user.getPassword());
        if (retrievedUser == null) {
            throw new InvalidCredentialsException();
        }
        String token = "{\"token\":"+"\""+securityTokenGenerator.createToken(user)+"\"}";
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
