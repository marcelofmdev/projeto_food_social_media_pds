package br.edu.ufrn.foodium.framework.domain.model.recommendation;

import br.edu.ufrn.foodium.framework.domain.model.Tag;

import java.util.List;

public interface TagRecommendable {
    List<Tag> getRecommendableTags();
}
