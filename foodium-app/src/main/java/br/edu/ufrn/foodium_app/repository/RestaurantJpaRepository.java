package br.edu.ufrn.foodium_app.repository;

import br.edu.ufrn.foodium_app.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RestaurantJpaRepository extends JpaRepository<Restaurant, Long> {

}
