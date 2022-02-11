package br.edu.ufrn.foodium.framework.controller;

import br.edu.ufrn.foodium.framework.controller.dto.user.CreateUserDto;
import br.edu.ufrn.foodium.framework.controller.dto.user.ResponseUserDto;
import br.edu.ufrn.foodium.framework.controller.dto.user.UpdateUserDto;
import br.edu.ufrn.foodium.framework.domain.model.User;
import br.edu.ufrn.foodium.framework.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<ResponseUserDto> getUser() {
        return userService.getUsers().stream().map(ResponseUserDto::new).toList();
    }

    @GetMapping("/{id}")
    public ResponseUserDto getUser(@PathVariable Long id) {
        return new ResponseUserDto(userService.getUser(id));
    }

    @PostMapping
    public ResponseEntity<ResponseUserDto> createUser(@Valid @RequestBody CreateUserDto user) {
        User savedUser = userService.saveUser(user);
        ResponseUserDto userDto = new ResponseUserDto(savedUser);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ResponseUserDto> updateUser(@Valid @RequestBody UpdateUserDto user) {
        User updatedUser = userService.updateUser(user);
        ResponseUserDto userDto = new ResponseUserDto(updatedUser);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePost(@PathVariable Long id) {
        userService.removeUser(id);
    }
}