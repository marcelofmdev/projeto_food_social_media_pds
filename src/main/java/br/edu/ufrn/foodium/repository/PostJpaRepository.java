package br.edu.ufrn.foodium.repository;

import br.edu.ufrn.foodium.domain.model.Post;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PostJpaRepository extends JpaRepository<Post, Long> {
    @Transactional
    @Modifying
    @Query("delete from Post p where p.id = ?1")
    void deleteById(@NonNull Long postId);
}
