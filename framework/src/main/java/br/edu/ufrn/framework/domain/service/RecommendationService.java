package br.edu.ufrn.framework.domain.service;

import br.edu.ufrn.framework.domain.model.Resource;
import br.edu.ufrn.framework.domain.model.recommendation.Recommendator;
import br.edu.ufrn.framework.domain.model.recommendation.TagRecommendable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public abstract class RecommendationService<T extends Resource> implements IRecommendationService<T> {
    private Recommendator recommendator;

    public float executeRecommentador(TagRecommendable source, TagRecommendable target) {
        return this.recommendator.recommend(source, target);
    }
}
