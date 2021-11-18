package br.edu.ufrn.foodium.repository;

import br.edu.ufrn.foodium.domain.model.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserJpaRepository extends JpaRepository<User, Long>{

    @Transactional
    @Modifying
    @Query("delete from User u where u.id = ?1")
    void deleteById(@NonNull Long userId);
}
