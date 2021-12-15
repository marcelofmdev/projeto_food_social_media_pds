package br.edu.ufrn.foodium.repository;

import br.edu.ufrn.foodium.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RestaurantJpaRepository extends JpaRepository<Restaurant, Long> {

}
