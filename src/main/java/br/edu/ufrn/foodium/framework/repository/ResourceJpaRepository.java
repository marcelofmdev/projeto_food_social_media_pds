package br.edu.ufrn.foodium.framework.repository;

import br.edu.ufrn.foodium.framework.domain.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceJpaRepository<T extends Resource> extends JpaRepository<T, Long> {
}
