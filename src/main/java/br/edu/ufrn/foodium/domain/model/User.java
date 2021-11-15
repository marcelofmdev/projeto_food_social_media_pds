package br.edu.ufrn.foodium.domain.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user", schema = "public")
public class User {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
    private Long id;

    private String name;

    private String userName;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();
}