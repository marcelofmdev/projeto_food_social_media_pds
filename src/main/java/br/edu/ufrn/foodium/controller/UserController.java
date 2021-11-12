package br.edu.ufrn.foodium.controller;

import br.edu.ufrn.foodium.domain.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/users")
    public User getUser(){
        return new User("Default User", "1", 40);
    }
}