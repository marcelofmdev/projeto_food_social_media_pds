package br.edu.ufrn.foodium.controller;

import br.edu.ufrn.foodium.controller.dto.user.CreateUserDto;
import br.edu.ufrn.foodium.controller.dto.user.ResponseUserDto;
import br.edu.ufrn.foodium.controller.dto.user.UpdateUserDto;
import br.edu.ufrn.foodium.domain.model.User;
import br.edu.ufrn.foodium.domain.service.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ResponseUserDto> putUser(@Valid @RequestBody UpdateUserDto user) {
        User updatedUser = userService.updateUser(user);
        ResponseUserDto userDto = new ResponseUserDto(updatedUser);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePost(@PathVariable Long id) {
        userService.removeUser(id);
    }
}