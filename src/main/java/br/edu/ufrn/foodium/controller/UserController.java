package br.edu.ufrn.foodium.controller;

import br.edu.ufrn.foodium.domain.model.Topic;
import br.edu.ufrn.foodium.domain.model.User;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/users")
    public User getUser(){
        return new User(1L, "Jubileu", List.of(Topic.values()));
    }
}