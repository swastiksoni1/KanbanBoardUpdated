package com.bej.kanbanService.proxy;

import com.bej.kanbanService.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="Useraurth",url="localhost:8083")
public interface UserProxy {

    // auto call the user auth save user with the register user of kanban service
    @PostMapping("/api/v2/save")
    public ResponseEntity<?> saveUser(@RequestBody User user);
}
