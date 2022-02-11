package br.edu.ufrn.foodium.framework.domain.model;

import br.edu.ufrn.foodium.framework.domain.model.recommendation.TagRecommendable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public abstract class Resource extends Auditable implements TagRecommendable {

    @Id
    @Column(nullable = false, name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
    protected Long id;

    @ManyToMany
    @JoinTable(
            name = "resource_tag",
            joinColumns = @JoinColumn(name = "resource_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    protected List<Tag> tags;

    @ManyToOne
    @JoinColumn(name = "user_id")
    protected User user;

    @JsonIgnore
    @Override
    public boolean isNew() {
        return this.id == null;
    }

    @JsonIgnore
    @Override
    public List<Tag> getRecommendableTags() {
        return getTags();
    }
}
