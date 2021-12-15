package br.edu.ufrn.foodium.domain.service;

import br.edu.ufrn.foodium.domain.exception.BusinessException;
import br.edu.ufrn.foodium.domain.model.Post;
import br.edu.ufrn.foodium.domain.model.User;
import br.edu.ufrn.foodium.domain.service.recommendation.Recommendator;
import br.edu.ufrn.foodium.domain.service.recommendation.TagRecommendable;
import br.edu.ufrn.foodium.domain.service.recommendation.Tuple;
import br.edu.ufrn.foodium.repository.PostJpaRepository;
import br.edu.ufrn.foodium.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class PostRecommendationService {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private PostJpaRepository postJpaRepository;

    @Autowired
    private Recommendator recommendator;

    public List<Post> getRecommendedPosts(Long userid) {
        User searchedUser = userJpaRepository.findById(userid).orElse(null);

        if(searchedUser == null) {
            throw new BusinessException("Usuário não encontrado com id " + userid, HttpStatus.NOT_FOUND.value());
        }

        List<Post> allPosts = postJpaRepository.findAll();

        if(allPosts.isEmpty()) {
            throw new BusinessException("Não há posts cadastrados", HttpStatus.NOT_FOUND.value());
        }

        return sortPostList(searchedUser, allPosts);
    }

    private List<Post> sortPostList(
            User source,
            List<Post> target
    ) {
        List<Tuple> indexedList = new ArrayList<>();

        for (TagRecommendable recommendable : target) {
            Float index = recommendator.recommend(source, recommendable);
            Tuple tuple = new Tuple(index, recommendable);
            indexedList.add(tuple);
        }

        // Greatest to the lowest index
        indexedList.sort(Comparator.reverseOrder());
        List<Post> tagsSortedList = new ArrayList<>();

        for (Tuple tuple : indexedList) {
            tagsSortedList.add((Post) tuple.getValue());
        }

        return ((List<Post>) tagsSortedList);
    }
}
