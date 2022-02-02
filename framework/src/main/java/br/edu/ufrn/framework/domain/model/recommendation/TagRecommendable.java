package br.edu.ufrn.framework.domain.model.recommendation;

import br.edu.ufrn.framework.domain.model.Tag;

import java.util.List;

public interface TagRecommendable {
    List<Tag> getRecommendableTags();
}
