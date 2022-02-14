package br.edu.ufrn.foodium.controller;

import br.edu.ufrn.foodium.domain.model.Post;
import br.edu.ufrn.foodium.service.PostRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/recommendation")
public class RecommendationController {

    @Autowired
    private PostRecommendationService postRecommendationService;

    @GetMapping("/{userid}")
    public List<Post> getRecommendedPosts(@PathVariable Long userid) {
        return postRecommendationService.getRecommendedResources(userid);
    }
}
