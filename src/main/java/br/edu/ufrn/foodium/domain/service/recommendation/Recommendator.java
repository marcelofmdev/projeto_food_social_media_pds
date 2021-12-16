package br.edu.ufrn.foodium.domain.service.recommendation;

import br.edu.ufrn.foodium.domain.model.Tag;

import java.util.List;

public interface Recommendator {
    public float recommend(TagRecommendable source, TagRecommendable target);
}
