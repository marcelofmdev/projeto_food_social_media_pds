package br.edu.ufrn.bookium.framework.domain.model.recommendation;

public interface Recommendator {
    float recommend(TagRecommendable source, TagRecommendable target);
}
