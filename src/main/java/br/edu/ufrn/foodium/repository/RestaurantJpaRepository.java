package br.edu.ufrn.foodium.repository;

import br.edu.ufrn.foodium.domain.model.Restaurant;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface RestaurantJpaRepository  extends JpaRepository<Restaurant, Long> {

    @Transactional
    @Modifying
    @Query("delete from Restaurant u where u.id = ?1")
    void deleteById(@NonNull Long restaurantId);
}
