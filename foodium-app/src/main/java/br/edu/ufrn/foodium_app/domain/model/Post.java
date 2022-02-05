package br.edu.ufrn.foodium_app.domain.model;

import br.edu.ufrn.framework.domain.model.Resource;
import br.edu.ufrn.framework.domain.model.Tag;
import br.edu.ufrn.framework.domain.model.User;
import br.edu.ufrn.framework.domain.model.recommendation.TagRecommendable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "post", schema = "public")
public class Post extends Resource {

    public Post(String imageUrl, String content, User user) {
        this.imageUrl = imageUrl;
        this.content = content;
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany()
    @JoinTable(
            name = "user_post_like",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @ToString.Exclude
    private List<User> usersLikes = new ArrayList<>();

    @Column
    private String imageUrl;

    @Column
    private String content;

    @Column
    private Integer likes = 0;
}
