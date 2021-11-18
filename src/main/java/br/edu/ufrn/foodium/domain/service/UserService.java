package br.edu.ufrn.foodium.domain.service;

import br.edu.ufrn.foodium.controller.dto.user.CreateUserDto;
import br.edu.ufrn.foodium.controller.dto.user.UpdateUserDto;
import br.edu.ufrn.foodium.domain.exception.BusinessException;
import br.edu.ufrn.foodium.domain.model.Tag;
import br.edu.ufrn.foodium.domain.model.User;
import br.edu.ufrn.foodium.repository.TagJpaRepository;
import br.edu.ufrn.foodium.repository.UserJpaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private TagJpaRepository tagJpaRepository;

    public List<User> getUsers() {
        return userJpaRepository.findAll();
    }

    public User getUser(Long id) {
        User user = userJpaRepository.findById(id).orElse(null);

        if (user == null) {
            throw new BusinessException("Usuário não encontrado com id " + id, HttpStatus.NOT_FOUND.value());
        }

        return user;
    }

    public User saveUser(CreateUserDto userDto) {
        User newUser = new User(userDto.getName(), userDto.getUserName(), userDto.getPassword());

        if (userDto.getTagsIds() != null) {
            List<Tag> tags = tagJpaRepository.findAllById(userDto.getTagsIds());
            newUser.setTags(tags);
        }

        return userJpaRepository.saveAndFlush(newUser);
    }

    public User updateUser(UpdateUserDto userDto) {
        User user = userJpaRepository.findById(userDto.getId()).orElse(null);

        if (user == null) {
            throw new BusinessException("Usuário não encontrado com id " + userDto.getId(), HttpStatus.NOT_FOUND.value());
        }

        if (userDto.getName() != null) {
            user.setName(userDto.getName());
        }
        if (userDto.getUserName() != null) {
            user.setUserName(userDto.getUserName());
        }

        if (userDto.getTagsIds() != null) {
            List<Tag> tags = tagJpaRepository.findAllById(userDto.getTagsIds());
            user.setTags(tags);
        }

        return userJpaRepository.saveAndFlush(user);
    }

    public void removeUser(Long id) {
        userJpaRepository.deleteById(id);
    }
}
