package br.edu.ufrn.framework.domain.service;

import br.edu.ufrn.framework.domain.model.Resource;
import br.edu.ufrn.framework.domain.model.User;

import java.util.List;

public interface IRecommendationService<T extends Resource> {
    List<T> getRecommended(User source, List<T> target);
}
