package br.edu.ufrn.foodium.repository;

import br.edu.ufrn.foodium.domain.model.Tag;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TagJpaRepository extends JpaRepository<Tag, Long> {
}
