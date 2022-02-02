package br.edu.ufrn.foodium_app.domain.model;

import br.edu.ufrn.framework.domain.model.Resource;
import br.edu.ufrn.framework.domain.model.Tag;
import br.edu.ufrn.framework.domain.model.User;
import br.edu.ufrn.framework.domain.model.recommendation.TagRecommendable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post extends Resource {

    public Post(String imageUrl, String content, User user) {
        this.imageUrl = imageUrl;
        this.content = content;
        this.user = user;
    }

    private User user;

    private List<User> usersLikes = new ArrayList<>();

    private String imageUrl;

    private String content;

    private Integer likes = 0;
}
