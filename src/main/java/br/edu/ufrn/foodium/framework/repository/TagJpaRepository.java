package br.edu.ufrn.foodium.framework.repository;

import br.edu.ufrn.foodium.framework.domain.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagJpaRepository extends JpaRepository<Tag, Long> {
}
