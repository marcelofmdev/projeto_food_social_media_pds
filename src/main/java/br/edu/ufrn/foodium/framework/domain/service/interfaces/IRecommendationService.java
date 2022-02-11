package br.edu.ufrn.foodium.framework.domain.service.interfaces;

import br.edu.ufrn.foodium.framework.domain.model.Resource;
import br.edu.ufrn.foodium.framework.domain.model.User;

import java.util.List;

public interface IRecommendationService<T extends Resource> {
    List<T> getRecommendedResources(Long userId);

    List<T> getRecommended(User source, List<T> target);
}
