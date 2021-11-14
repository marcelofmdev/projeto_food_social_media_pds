package br.edu.ufrn.foodium.domain.service;

import br.edu.ufrn.foodium.domain.model.User;
import br.edu.ufrn.foodium.repository.UserJpaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserJpaRepository userJpaRepository;

    public List<User> getUsers() {
        return userJpaRepository.findAll();
    }

    public User getUser(Long id) {
        return userJpaRepository.findById(id).orElse(null);
    }

    public User saveUser(User user) {
        return userJpaRepository.save(user);
    }

    public void removeUser(Long id) {
        userJpaRepository.deleteById(id);
    }
}
