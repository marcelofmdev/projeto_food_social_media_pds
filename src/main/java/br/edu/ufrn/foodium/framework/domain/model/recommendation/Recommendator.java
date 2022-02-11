package br.edu.ufrn.foodium.framework.domain.model.recommendation;

public interface Recommendator {
    float recommend(TagRecommendable source, TagRecommendable target);
}
