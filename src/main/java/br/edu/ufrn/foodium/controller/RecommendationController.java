package br.edu.ufrn.foodium.controller;

import br.edu.ufrn.foodium.domain.model.Post;
import br.edu.ufrn.foodium.domain.service.PostRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/recommendation")
public class RecommendationController {

    /*@Autowired
    private final Recommendator recommendator;

    public RecommendatorController(Recommendator recommendator) {
        this.recommendator = recommendator;
    }*/

    @Autowired
    private PostRecommendationService postRecommendationService;

    @GetMapping("/{userid}")
    public List<Post> getRecommendedPosts(@PathVariable Long userid) {

        return postRecommendationService.getRecommendedPosts(userid);
    }

    /*@GetMapping("/test")
    public List<List<Long>> getResponse(@RequestBody requestDTO dto) {
        List<List<Long>> recommendationList = new ArrayList<>();

        recommendationList = RecommendationListing.sortRecommendationList(
                new JaccardRecommendator(),
                dto.source,
                dto.target
        );

        return recommendationList;
    }

    public static class requestDTO {
        public List<Long> source;
        public List<List<Long>> target;

        public requestDTO(List<Long> source, List<List<Long>> target) {
            this.source = source;
            this.target = target;
        }
    }*/
}
