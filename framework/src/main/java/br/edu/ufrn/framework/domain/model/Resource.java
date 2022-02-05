package br.edu.ufrn.framework.domain.model;

import br.edu.ufrn.framework.domain.model.recommendation.TagRecommendable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Resource extends Auditable implements TagRecommendable {

    @Id
    @Column(nullable = false, name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
    public Long id;

    @Column(nullable = false)
    public String name;

    @ManyToMany
    @JoinTable(
            name = "resource_tag",
            joinColumns = @JoinColumn(name = "resource_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    public List<Tag> tags;

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
