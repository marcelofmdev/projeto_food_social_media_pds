package br.edu.ufrn.foodium.service.recommendation;

import br.edu.ufrn.foodium.domain.model.Hotel;
import br.edu.ufrn.foodium.domain.model.Post;
import br.edu.ufrn.foodium.framework.domain.model.User;
import br.edu.ufrn.foodium.framework.domain.model.recommendation.TagRecommendable;
import br.edu.ufrn.foodium.framework.domain.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class HotelRecommendationService extends RecommendationService<Hotel> {

    @Override
    public List<Hotel> getRecommended(User source, List<Hotel> target) {
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
                double distance = calculateDistance(source, (Hotel) recommendable);
                ((Hotel) recommendable).setDistance(distance);

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
        List<Hotel> sortedPostList = new ArrayList<>();

        for (Tuple tuple : indexedList) {
            sortedPostList.add((Hotel) tuple.getValue());
        }

        return sortedPostList;
    }

    private double calculateDistance(User user, Hotel hotel) {
        double rlat1 = (user.getLocLatitude() * Math.PI)/180;
        double rlon1 = (user.getLocLongitude() * Math.PI)/180;
        double rlat2 = (hotel.getLocLatitude() * Math.PI)/180;
        double rlon2 = (hotel.getLocLongitude() * Math.PI)/180;

        double drlat = rlat2 - rlat1;
        double drlon = rlon2 - rlon1;

        double init = Math.pow(Math.sin(drlat/2), 2) +
                (Math.cos(rlat1)) * (Math.cos(rlat2)) *
                        Math.pow((Math.sin(drlon/2)), 2);

        double R = 6367;

        return 2.0 * R * Math.asin(Math.min(1.0, Math.sqrt(init))); // in Km
    }
}
