package br.edu.ufrn.foodium_app.domain.service;

import br.edu.ufrn.foodium_app.domain.model.Post;
import br.edu.ufrn.foodium_app.domain.service.recommendation.JaccardRecommendator;
import br.edu.ufrn.foodium_app.domain.service.recommendation.Tuple;
import br.edu.ufrn.foodium_app.repository.PostJpaRepository;
import br.edu.ufrn.framework.domain.exception.NotFoundException;
import br.edu.ufrn.framework.domain.model.User;
import br.edu.ufrn.framework.domain.model.recommendation.TagRecommendable;
import br.edu.ufrn.framework.domain.service.RecommendationService;
import br.edu.ufrn.framework.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class PostRecommendationService extends RecommendationService<Post> {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private PostJpaRepository postJpaRepository;

    public List<Post> getRecommendedPosts(Long userid) {
        User searchedUser = userJpaRepository.findById(userid).orElse(null);

        if(searchedUser == null) {
            throw new NotFoundException("Usuário não encontrado com id " + userid);
        }

        List<Post> allPosts = postJpaRepository.findAll();

        if(allPosts.isEmpty()) {
            return allPosts;
        }

        return getRecommended(searchedUser, allPosts);
    }

    @Override
    public List<Post> getRecommended(User source, List<Post> target) {
        if(source.getTags().isEmpty() || target.isEmpty()) {
            return new ArrayList<>();
        }

        setRecommendator(new JaccardRecommendator());

        List<Tuple> indexedList = new ArrayList<>();

        if(getRecommendator() == null) {
            return new ArrayList<>();
        }

        for (TagRecommendable recommendable : target) {
            try {
                Float index = executeRecommentador(source, recommendable);
                Tuple tuple = new Tuple(index, recommendable);
                indexedList.add(tuple);
            }
            catch (NullPointerException e) {
                // Iteration continues
            }
        }

        if(indexedList.isEmpty()) {
            return new ArrayList<>();
        }

        // Greatest to the lowest index
        indexedList.sort(Comparator.reverseOrder());
        List<Post> sortedPostList = new ArrayList<>();

        for (Tuple tuple : indexedList) {
            sortedPostList.add((Post) tuple.getValue());
        }

        return sortedPostList;
    }
}
