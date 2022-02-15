package br.edu.ufrn.bookium.framework.repository;

import br.edu.ufrn.bookium.framework.domain.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceJpaRepository<T extends Resource> extends JpaRepository<T, Long> {
}
