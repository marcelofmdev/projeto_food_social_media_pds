package br.edu.ufrn.foodium.framework.domain.service;

import br.edu.ufrn.foodium.framework.domain.exception.NotFoundException;
import br.edu.ufrn.foodium.framework.domain.model.Resource;
import br.edu.ufrn.foodium.framework.domain.model.User;
import br.edu.ufrn.foodium.framework.domain.model.recommendation.Recommendator;
import br.edu.ufrn.foodium.framework.domain.model.recommendation.TagRecommendable;
import br.edu.ufrn.foodium.framework.domain.service.interfaces.IRecommendationService;
import br.edu.ufrn.foodium.framework.repository.ResourceJpaRepository;
import br.edu.ufrn.foodium.framework.repository.UserJpaRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Service
public abstract class RecommendationService<T extends Resource> implements IRecommendationService<T> {
    protected Recommendator recommendator;

    @Autowired
    protected UserJpaRepository userJpaRepository;

    @Autowired
    protected ResourceJpaRepository<T> resourceJpaRepository;

    public List<T> getRecommendedResources(Long userid) {
        User searchedUser = userJpaRepository.findById(userid).orElse(null);

        if(searchedUser == null) {
            throw new NotFoundException("Usuário não encontrado com id " + userid);
        }

        List<T> allResources = resourceJpaRepository.findAll();

        if(allResources.isEmpty()) {
            return allResources;
        }

        return getRecommended(searchedUser, allResources);
    }

    public float executeRecommendator(TagRecommendable source, TagRecommendable target) {
        return this.recommendator.recommend(source, target);
    }
}
