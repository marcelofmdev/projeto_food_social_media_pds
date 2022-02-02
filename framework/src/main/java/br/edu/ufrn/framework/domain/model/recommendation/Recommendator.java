package br.edu.ufrn.framework.domain.model.recommendation;

public interface Recommendator {
    float recommend(TagRecommendable source, TagRecommendable target);
}
