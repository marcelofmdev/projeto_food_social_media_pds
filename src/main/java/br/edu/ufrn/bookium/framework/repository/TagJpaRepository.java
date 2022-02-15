package br.edu.ufrn.bookium.framework.repository;

import br.edu.ufrn.bookium.framework.domain.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagJpaRepository extends JpaRepository<Tag, Long> {
}