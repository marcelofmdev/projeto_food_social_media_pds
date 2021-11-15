package br.edu.ufrn.foodium.repository;

import br.edu.ufrn.foodium.domain.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagJpaRepository extends JpaRepository<Tag, Long> {

}
