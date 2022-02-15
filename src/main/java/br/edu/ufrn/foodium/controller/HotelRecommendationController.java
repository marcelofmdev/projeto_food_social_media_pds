package br.edu.ufrn.foodium.controller;

import br.edu.ufrn.foodium.domain.model.Hotel;
import br.edu.ufrn.foodium.domain.model.Post;
import br.edu.ufrn.foodium.service.HotelRecommendationService;
import br.edu.ufrn.foodium.service.PostRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/recommendation/hotel")
public class HotelRecommendationController {

    @Autowired
    private HotelRecommendationService hotelRecommendationService;

    @GetMapping("/{userid}")
    public List<Hotel> getRecommendedPosts(@PathVariable Long userid) {
        return hotelRecommendationService.getRecommendedResources(userid);
    }
}
