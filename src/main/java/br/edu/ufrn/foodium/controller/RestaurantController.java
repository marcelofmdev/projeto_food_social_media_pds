package br.edu.ufrn.foodium.controller;

import br.edu.ufrn.foodium.controller.dto.restaurant.CreateRestaurantDto;
import br.edu.ufrn.foodium.controller.dto.restaurant.UpdateRestaurantDto;
import br.edu.ufrn.foodium.domain.model.Restaurant;
import br.edu.ufrn.foodium.domain.service.RestaurantService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> getRestaurants() {
        return restaurantService.getRestaurants();
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurant(@PathVariable Long id) {
        return restaurantService.getRestaurant(id);
    }

    @PostMapping
    public Restaurant createRestaurant(@Valid @RequestBody CreateRestaurantDto restaurant) {
        return restaurantService.saveRestaurant(restaurant);
    }

    @PutMapping
    public Restaurant updateRestaurant(@Valid @RequestBody UpdateRestaurantDto restaurant) {
        return restaurantService.updateRestaurant(restaurant);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteRestaurant(@PathVariable Long id) {
        restaurantService.removeRestaurant(id);
    }
}
