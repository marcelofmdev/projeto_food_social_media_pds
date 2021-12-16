package br.edu.ufrn.foodium.domain.service.recommendation;

import br.edu.ufrn.foodium.domain.model.Tag;

import java.util.List;

public interface TagRecommendable {
    public List<Tag> getRecommendableTags();
}
