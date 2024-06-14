package com.example.userservice.controllers;

import com.example.userservice.commands.UpdateUserCommand;
import com.example.userservice.query.SearchParams;
import com.example.userservice.query.requests.RequestUserData;
import com.example.userservice.responses.UserResponse;
import com.example.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/search")
    public List<UserResponse> search(@RequestBody SearchParams searchParams){
        return userService.search(searchParams);
    }

    @PostMapping("/create")
    public String create(@RequestBody RequestUserData user){
        userService.createUser(user);
        return "User created " + user.getLogin() + " " + user.getRoles().toString();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        userService.deleteUser(id);
        return "User deleted " + id;
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id, @RequestBody UpdateUserCommand user){
        userService.update(id, user);
        return "User updated "
                + user.getEmail()
                + " "
                + user.getLogin()
                + " "
                + user.getPassword()
                + " "
                + user.getFirstname()
                + " "
                + user.getLastname();
    }
}
