package br.edu.ufrn.foodium.controller;

import br.edu.ufrn.foodium.domain.model.Post;
import br.edu.ufrn.foodium.service.PostRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
