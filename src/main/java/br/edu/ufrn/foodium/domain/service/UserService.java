package br.edu.ufrn.foodium.domain.service;

import br.edu.ufrn.foodium.controller.dto.user.CreateUserDto;
import br.edu.ufrn.foodium.controller.dto.user.UpdateUserDto;
import br.edu.ufrn.foodium.domain.exception.NotFoundException;
import br.edu.ufrn.foodium.domain.model.Tag;
import br.edu.ufrn.foodium.domain.model.User;
import br.edu.ufrn.foodium.repository.UserJpaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private TagService tagService;

    public List<User> getUsers() {
        return userJpaRepository.findAll();
    }

    public User getUser(Long id) {
        User user = userJpaRepository.findById(id).orElse(null);

        if (user == null) {
            throw new NotFoundException("Usuário não encontrado com id " + id);
        }

        return user;
    }

    @Transactional
    public User saveUser(CreateUserDto userDto) {
        User newUser = new User(userDto.getName(), userDto.getUserName(), userDto.getPassword());

        if (userDto.getTagsIds() != null) {
            List<Tag> tags = tagService.getAllByIds(userDto.getTagsIds());
            newUser.setTags(tags);
        }

        return userJpaRepository.save(newUser);
    }

    @Transactional
    public User updateUser(UpdateUserDto userDto) {
        User user = userJpaRepository.findById(userDto.getId()).orElse(null);

        if (user == null) {
            throw new NotFoundException("Usuário não encontrado com id " + userDto.getId());
        }

        if (userDto.getName() != null) {
            user.setName(userDto.getName());
        }
        if (userDto.getUserName() != null) {
            user.setUserName(userDto.getUserName());
        }

        if (userDto.getTagsIds() != null) {
            List<Tag> tags = tagService.getAllByIds(userDto.getTagsIds());
            user.setTags(tags);
        }

        return userJpaRepository.saveAndFlush(user);
    }

    @Transactional
    public void removeUser(Long id) {
        try {
            userJpaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new NotFoundException("Usuário não encontrado com id " + id);
        }
    }
}
