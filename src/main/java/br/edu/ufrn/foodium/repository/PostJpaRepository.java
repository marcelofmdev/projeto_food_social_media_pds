package br.edu.ufrn.foodium.repository;

import br.edu.ufrn.foodium.domain.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<Post, Long> {

}
