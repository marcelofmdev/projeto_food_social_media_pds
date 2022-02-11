package br.edu.ufrn.foodium.framework.domain.model;

import br.edu.ufrn.foodium.framework.domain.model.recommendation.TagRecommendable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_foodium", schema = "public")
public class User extends Auditable implements TagRecommendable {

    public User(String name, String userName, String password) {
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

    public User(Long id, String name, String userName) {
        this.id = id;
        this.name = name;
        this.userName = userName;
    }

    @Id
    @Column(nullable = false, name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
    private Long id;

    @Column
    private String name;

    @Column(name = "user_name")
    private String userName;

    @Column
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Resource> resources = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_tag",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags = new ArrayList<>();

    @JsonIgnore
    @Override
    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public List<Tag> getRecommendableTags() {
        return getTags();
    }
}