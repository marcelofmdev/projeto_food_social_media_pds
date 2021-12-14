package br.edu.ufrn.foodium.controller.test;

import br.edu.ufrn.foodium.domain.model.Tag;
import br.edu.ufrn.foodium.domain.service.recommendation.JaccardRecommendator;
import br.edu.ufrn.foodium.domain.service.recommendation.RecommendationListing;
import br.edu.ufrn.foodium.domain.service.recommendation.Recommendator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "api/recommendation")
public class RecommendatorController {

    @Autowired
    private final Recommendator recommendator;

    public RecommendatorController(Recommendator recommendator) {
        this.recommendator = recommendator;
    }

    @GetMapping
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
    }
}
