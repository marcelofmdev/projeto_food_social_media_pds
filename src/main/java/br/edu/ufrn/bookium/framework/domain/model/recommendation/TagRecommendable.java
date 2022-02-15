package br.edu.ufrn.bookium.framework.domain.model.recommendation;

import br.edu.ufrn.bookium.framework.domain.model.Tag;

import java.util.List;

public interface TagRecommendable {
    List<Tag> getRecommendableTags();
}
