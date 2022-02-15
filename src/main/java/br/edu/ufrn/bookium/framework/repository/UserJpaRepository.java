package br.edu.ufrn.bookium.framework.repository;

import br.edu.ufrn.bookium.framework.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
}
