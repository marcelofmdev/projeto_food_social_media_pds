package br.edu.ufrn.framework.repository;

import br.edu.ufrn.framework.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {

}
