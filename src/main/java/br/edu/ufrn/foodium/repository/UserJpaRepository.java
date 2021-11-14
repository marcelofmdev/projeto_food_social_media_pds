package br.edu.ufrn.foodium.repository;

import br.edu.ufrn.foodium.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long>{

}
