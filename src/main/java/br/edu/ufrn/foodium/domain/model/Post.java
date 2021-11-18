package br.edu.ufrn.foodium.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.CreatedDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "post", schema = "public")
public class Post  extends Auditable {

    public Post(String imageUrl, String content, User user) {
        this.imageUrl = imageUrl;
        this.content = content;
        this.user = user;
    }


    @Id
    @Column(nullable = false, name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany()
    @JoinTable(
            name = "tag_post",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    public List<Tag> tags = new ArrayList<>();

    @Column(name = "image_url")
    private String imageUrl;

    @Column
    private String content;

    @Override
    public Long getId() {
        return this.id;
    }

    @JsonIgnore
    @Override
    public boolean isNew() {
        return this.id != null;
    }
}
