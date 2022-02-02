package br.edu.ufrn.framework.repository;

import br.edu.ufrn.framework.domain.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagJpaRepository extends JpaRepository<Tag, Long> {

}
