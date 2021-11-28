package br.edu.ufrn.foodium.domain.service.recommendation;

import br.edu.ufrn.foodium.domain.model.Tag;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JaccardRecommendator implements Recommendator{
    @Override
    public float recommend(List<Long> sourceTags, List<Long> targetTags) {
        float index = calculateIndex(sourceTags, targetTags);
        return index;
    }
    
    private Float calculateIndex(List<Long> sourceTags, List<Long> targetTags) {
        float index = 0f;
        int union = 0;
        int intersection = 0;

        List<Long> intersectionTags = new ArrayList<>();

        for (Long sourceTag:sourceTags) {
            for (Long targetTag:targetTags) {
                if(sourceTag.equals(targetTag)) {
                    intersectionTags.add(sourceTag);
                    break;
                }
            }
        }

        intersection = intersectionTags.size();
        union = sourceTags.size() + targetTags.size() - intersection;
        index = ((float) intersection) / ((float) union);

        return index;
    }
}
