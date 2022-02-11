package br.edu.ufrn.foodium.framework.repository;

import br.edu.ufrn.foodium.framework.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
}
