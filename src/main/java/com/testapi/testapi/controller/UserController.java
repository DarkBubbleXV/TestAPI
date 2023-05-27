package com.testapi.testapi.controller;

import com.testapi.testapi.dto.RespondDto;
import com.testapi.testapi.model.User;
import com.testapi.testapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<RespondDto<User>> addUser(@Valid @RequestBody User user, Errors errors){

        RespondDto<User> userRespondDto = new RespondDto<>();
        if(errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()){
                userRespondDto.getMessage().add(error.getDefaultMessage());
            }
            userRespondDto.setStatus(false);
            userRespondDto.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userRespondDto);
        }
        userRespondDto.setStatus(true);
        userRespondDto.setPayload(userService.addUser(user));
        return ResponseEntity.ok(userRespondDto);
    }

    @GetMapping
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") Long userId){
        return userService.findUserById(userId);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long userId){
     userService.removeUser(userId);
    }
}
