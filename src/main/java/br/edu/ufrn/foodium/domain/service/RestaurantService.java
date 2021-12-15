package br.edu.ufrn.foodium.domain.service;

import br.edu.ufrn.foodium.controller.dto.restaurant.CreateRestaurantDto;
import br.edu.ufrn.foodium.controller.dto.restaurant.UpdateRestaurantDto;
import br.edu.ufrn.foodium.domain.exception.BusinessException;
import br.edu.ufrn.foodium.domain.exception.NotFoundException;
import br.edu.ufrn.foodium.domain.model.Restaurant;
import br.edu.ufrn.foodium.domain.model.Tag;
import br.edu.ufrn.foodium.repository.RestaurantJpaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantJpaRepository restaurantJpaRepository;

    @Autowired
    private TagService tagService;

    public List<Restaurant> getRestaurants() {
        return restaurantJpaRepository.findAll();
    }

    public Restaurant getRestaurant(Long id) {
        Restaurant restaurant = restaurantJpaRepository.findById(id).orElse(null);

        if (restaurant == null) {
            throw new NotFoundException("Restaurante não encontrado com id " + id);
        }

        return restaurant;
    }

    public Restaurant saveRestaurant(CreateRestaurantDto restaurantDto) {
        Restaurant newRestaurant = new Restaurant(restaurantDto.getName(), restaurantDto.getDescription(), restaurantDto.getLogo());

        if (restaurantDto.getTagsIds() != null) {
            List<Tag> tags = tagService.getAllByIds(restaurantDto.getTagsIds());
            newRestaurant.setTags(tags);
        }

        return restaurantJpaRepository.save(newRestaurant);
    }

    public Restaurant updateRestaurant(UpdateRestaurantDto restaurantDto) {
        Restaurant restaurant = restaurantJpaRepository.findById(restaurantDto.getId()).orElse(null);

        if (restaurant == null) {
            throw new NotFoundException("Restaurante não encontrado com id " + restaurantDto.getId());
        }

        if (restaurantDto.getName() != null) {
            restaurant.setName(restaurantDto.getName());
        }
        if (restaurantDto.getDescription() != null) {
            restaurant.setDescription(restaurantDto.getDescription());
        }
        if (restaurantDto.getLogo() != null) {
            restaurant.setLogo(restaurantDto.getLogo());
        }

        if (restaurantDto.getTagsIds() != null) {
            List<Tag> tags = tagService.getAllByIds(restaurantDto.getTagsIds());
            restaurant.setTags(tags);
        }

        return restaurantJpaRepository.saveAndFlush(restaurant);
    }

    public void removeRestaurant(Long id) {
        restaurantJpaRepository.deleteById(id);
    }
}
