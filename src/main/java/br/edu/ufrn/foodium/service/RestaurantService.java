package br.edu.ufrn.foodium.service;

import br.edu.ufrn.foodium.controller.dto.restaurant.CreateRestaurantDto;
import br.edu.ufrn.foodium.controller.dto.restaurant.UpdateRestaurantDto;
import br.edu.ufrn.foodium.framework.domain.exception.NotFoundException;
import br.edu.ufrn.foodium.domain.model.Restaurant;
import br.edu.ufrn.foodium.framework.domain.model.Tag;
import br.edu.ufrn.foodium.framework.domain.service.ResourceService;
import br.edu.ufrn.foodium.framework.domain.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantService extends ResourceService<Restaurant> {

    @Autowired
    private TagService tagService;

    @Transactional
    public Restaurant saveRestaurant(CreateRestaurantDto restaurantDto) {
        Restaurant newRestaurant = new Restaurant(restaurantDto.getName(), restaurantDto.getDescription(), restaurantDto.getLogo());

        if (restaurantDto.getTagsIds() != null) {
            List<Tag> tags = tagService.getAllByIds(restaurantDto.getTagsIds());
            newRestaurant.setTags(tags);
        }

        return resourceJpaRepository.save(newRestaurant);
    }

    @Transactional
    public Restaurant updateRestaurant(UpdateRestaurantDto restaurantDto) {
        Restaurant restaurant = resourceJpaRepository.findById(restaurantDto.getId()).orElse(null);

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

        return resourceJpaRepository.saveAndFlush(restaurant);
    }

}
