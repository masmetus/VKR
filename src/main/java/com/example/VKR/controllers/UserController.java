package com.example.VKR.controllers;


import com.example.VKR.dto.auth.response.MessageResponse;
import com.example.VKR.dto.some.general.DeleteRequest;
import com.example.VKR.dto.some.room.ChangeRoomRequest;
import com.example.VKR.dto.user.ChangeEmailRequest;
import com.example.VKR.dto.user.ChangeUsernameRequest;
import com.example.VKR.dto.user.UserResponse;
import com.example.VKR.model.User;
import com.example.VKR.repository.UserRepository;
import com.example.VKR.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/user")
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("/details")
    @ApiOperation("Информация о пользователе")
    public ResponseEntity<UserResponse> userDetails(Principal principal){
        User user = userRepository.findByUsername(principal.getName()).get();
        return ResponseEntity.ok(userService.getDetails(user));
    }

    @PutMapping("/changeEmail")
    @ApiOperation("Изменить почту")
    public ResponseEntity<MessageResponse> changeEmail(@Valid @RequestBody ChangeEmailRequest changeEmailRequest, Principal principal){
        User user = userRepository.findByUsername(principal.getName()).get();
        if(userService.changeEmail(user, changeEmailRequest.getEmail()))
            return ResponseEntity.ok(new MessageResponse("Email change successful"));
        else
            return ResponseEntity.badRequest().body(new MessageResponse("Error"));
    }

    @PutMapping("/changeUsername")
    @ApiOperation("Изменить логин")
    public ResponseEntity<MessageResponse> changeUsername(@Valid @RequestBody ChangeUsernameRequest changeUsernameRequest, Principal principal){
        User user = userRepository.findByUsername(principal.getName()).get();
        if(userService.changeUsername(user, changeUsernameRequest.getUsername()))
            return ResponseEntity.ok(new MessageResponse("Username change successful"));
        else
            return ResponseEntity.badRequest().body(new MessageResponse("Error"));
    }


    @PutMapping("/changeUser/{id}")
    @ApiOperation("Изменить данные пользователя")
    public ResponseEntity<MessageResponse> changeUser(@Valid @RequestBody User user, @PathVariable("id") Long id){
        if(userService.changeUser(id, user))
            return ResponseEntity.ok(new MessageResponse("Username change successful"));
        else
            return ResponseEntity.badRequest().body(new MessageResponse("Error"));

    }

    @DeleteMapping("/delUser")
    @ApiOperation("Удаление пользователя")
    public ResponseEntity<MessageResponse> delUser(@Valid @RequestBody DeleteRequest deleteRequest){
        if(userService.deleteUser(deleteRequest.getId()))
            return ResponseEntity.ok(new MessageResponse("User has been deleted"));
        else
            return ResponseEntity.badRequest().body(new MessageResponse("Data integrity violation error"));
    }

    @GetMapping("/allUser")
    @ApiOperation("Список пользователей")
    public ResponseEntity<List<User>> allUser(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
