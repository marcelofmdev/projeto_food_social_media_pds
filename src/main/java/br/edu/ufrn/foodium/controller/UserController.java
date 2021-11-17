package br.edu.ufrn.foodium.controller;

import br.edu.ufrn.foodium.domain.model.User;
import br.edu.ufrn.foodium.domain.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUser() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping
    public User putUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePost(@PathVariable Long id) {
        userService.removeUser(id);
    }
}