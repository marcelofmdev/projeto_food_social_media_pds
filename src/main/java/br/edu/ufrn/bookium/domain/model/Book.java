package br.edu.ufrn.bookium.domain.model;

import br.edu.ufrn.bookium.framework.domain.model.Resource;
import br.edu.ufrn.bookium.framework.domain.model.User;
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
@Table(name = "book", schema = "public")
public class Book extends Resource {

    public Book(String imageUrl, String content, User user) {
        this.imageUrl = imageUrl;
        this.content = content;
        this.user = user;
    }

    @ManyToMany()
    @JoinTable(
            name = "user_book_like",
            joinColumns = @JoinColumn(name = "book_id"),
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
