package br.edu.ufrn.foodium.domain.service.recommendation;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@Service
public class RecommendationListing {

    public static List<List<Long>> sortRecommendationList(
            Recommendator recommendator,
            List<Long> source,
            List<List<Long>> target
    ) {
        List<Tuple> indexList = new ArrayList<>();

        for (List<Long> list : target) {
            Float index = recommendator.recommend(source, list);
            Tuple tuple = new Tuple(index, list);
            indexList.add(tuple);
        }

        indexList.sort(Comparator.reverseOrder());
        List<List<Long>> tagsSortedList = new ArrayList<>();

        for (Tuple tuple : indexList) {
            tagsSortedList.add((List<Long>) tuple.getValue());
        }

        return tagsSortedList;
    }
}
