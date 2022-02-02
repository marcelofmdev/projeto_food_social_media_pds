package br.edu.ufrn.foodium.domain.service.recommendation;

public class RecommendationContext {
    private Recommendator recomendator;

    public void setReconmendator(Recommendator recomendator) {
        this.recomendator = recomendator;
    }

    public float executeRecomentador (TagRecommendable source, TagRecommendable targert) {
        return this.recomendator.recommend(source, targert);
    }

}
