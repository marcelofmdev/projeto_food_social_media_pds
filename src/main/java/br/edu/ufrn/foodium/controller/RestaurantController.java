package br.edu.ufrn.foodium.controller;

import br.edu.ufrn.foodium.controller.dto.restaurant.CreateRestaurantDto;
import br.edu.ufrn.foodium.controller.dto.restaurant.UpdateRestaurantDto;
import br.edu.ufrn.foodium.domain.model.Restaurant;
import br.edu.ufrn.foodium.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> getRestaurants() {
        return restaurantService.getAll();
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurant(@PathVariable Long id) {
        return restaurantService.getOne(id);
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
        restaurantService.remove(id);
    }
}
