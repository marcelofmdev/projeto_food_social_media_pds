package br.edu.ufrn.framework.repository;

import br.edu.ufrn.framework.domain.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceJpaRepository<T extends Resource> extends JpaRepository<T, Long> {
}
