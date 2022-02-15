package br.edu.ufrn.foodium.service.recommendation;

import br.edu.ufrn.foodium.domain.model.Hotel;
import br.edu.ufrn.foodium.framework.domain.model.User;
import br.edu.ufrn.foodium.framework.domain.model.recommendation.Recommendator;
import br.edu.ufrn.foodium.framework.domain.model.recommendation.TagRecommendable;
import br.edu.ufrn.foodium.service.HotelRecommendationService;

public class HotelRecommendator implements Recommendator {
    @Override
    public float recommend(TagRecommendable source, TagRecommendable target) {
        float distance = calculateDistance((User) source, (Hotel) target);
        return new JaccardRecommendator()
                .recommend((TagRecommendable) source, (TagRecommendable) target)*1/distance;
    }

    private float calculateDistance(User user, Hotel hotel) {
        return (float) HotelRecommendationService.calculateDistance(user, hotel);
    }
}
