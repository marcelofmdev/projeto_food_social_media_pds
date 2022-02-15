package br.edu.ufrn.bookium.service;

import br.edu.ufrn.bookium.domain.model.Book;
import br.edu.ufrn.bookium.service.recommendation.JaccardRecommendator;
import br.edu.ufrn.bookium.service.recommendation.Tuple;
import br.edu.ufrn.bookium.framework.domain.model.User;
import br.edu.ufrn.bookium.framework.domain.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class BookRecommendationService extends RecommendationService<Book> {

    @Override
    public List<Book> getRecommended(User source, List<Book> target) {
        if(source.getTags().isEmpty() || target.isEmpty()) {
            return new ArrayList<>();
        }

        setRecommendator(new JaccardRecommendator());

        List<Tuple> indexedList = new ArrayList<>();

        if(getRecommendator() == null) {
            return new ArrayList<>();
        }

        try {
            for (Book recommendable : target) {
                int numLikes = recommendable.getLikes();
                try {
                    Float index = executeRecommentador(source, recommendable) + numLikes;
                    Tuple tuple = new Tuple(index, recommendable);
                    indexedList.add(tuple);
                } catch (NullPointerException e) {
                    // Iteration continues
                }
            }
        }catch (Exception ignored){}

        if(indexedList.isEmpty()) {
            return new ArrayList<>();
        }

        // Greatest to the lowest index
        indexedList.sort(Comparator.reverseOrder());
        List<Book> sortedBookList = new ArrayList<>();

        for (Tuple tuple : indexedList) {
            sortedBookList.add((Book) tuple.getValue());
        }

        return sortedBookList;
    }
}
