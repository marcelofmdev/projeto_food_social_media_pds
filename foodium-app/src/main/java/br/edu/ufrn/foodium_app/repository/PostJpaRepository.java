package br.edu.ufrn.foodium_app.repository;

import br.edu.ufrn.foodium_app.domain.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<Post, Long> {

}
