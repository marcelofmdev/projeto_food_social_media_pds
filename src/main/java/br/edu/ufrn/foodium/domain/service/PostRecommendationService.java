package br.edu.ufrn.foodium.domain.service;

import br.edu.ufrn.foodium.domain.exception.NotFoundException;
import br.edu.ufrn.foodium.domain.model.Post;
import br.edu.ufrn.foodium.domain.model.User;
import br.edu.ufrn.foodium.domain.service.recommendation.*;
import br.edu.ufrn.foodium.repository.PostJpaRepository;
import br.edu.ufrn.foodium.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @Autowired
//    private Recommendator recommendator;

    public List<Post> getRecommendedPosts(Long userid) {
        User searchedUser = userJpaRepository.findById(userid).orElse(null);

        if(searchedUser == null) {
            throw new NotFoundException("Usuário não encontrado com id " + userid);
        }

        List<Post> allPosts = postJpaRepository.findAll();

        if(allPosts.isEmpty()) {
            return allPosts;
        }

        return sortPostList(searchedUser, allPosts);
    }

    private List<Post> sortPostList(User source, List<Post> target) {
        if(source.getTags().isEmpty() || target.isEmpty()) {
            return new ArrayList<>();
        }

        RecommendationContext recommendator = new RecommendationContext();

        recommendator.setReconmendator(new JaccardRecommendator());

        List<Tuple> indexedList = new ArrayList<>();

        for (TagRecommendable recommendable : target) {
            try {
                Float index = recommendator.executeRecomentador(source, recommendable);
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
