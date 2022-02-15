package br.edu.ufrn.foodium.service;

import br.edu.ufrn.foodium.domain.model.Post;
import br.edu.ufrn.foodium.service.recommendation.JaccardRecommendator;
import br.edu.ufrn.foodium.service.recommendation.Tuple;
import br.edu.ufrn.foodium.framework.domain.model.User;
import br.edu.ufrn.foodium.framework.domain.model.recommendation.TagRecommendable;
import br.edu.ufrn.foodium.framework.domain.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class PostRecommendationService extends RecommendationService<Post> {

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
                Float index = executeRecommendator(source, recommendable);
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
