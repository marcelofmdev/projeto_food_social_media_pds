package br.edu.ufrn.foodium.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "post", schema = "public")
public class Post {

    public Post(String imageUrl, String content, LocalDate date, User user) {
        this.image_url = imageUrl;
        this.content = content;
        this.date = date;
        this.user = user;
    }


    public Post(String imageUrl, String content, LocalDate date) {
        this.image_url = imageUrl;
        this.content = content;
        this.date = date;
    }

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
    private Long post_id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToMany()
    @JoinTable(
            name = "tag_post",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    public List<Tag> tag_posts = new ArrayList<>();

    private String image_url;

    private String content;

    private LocalDate date;
}
