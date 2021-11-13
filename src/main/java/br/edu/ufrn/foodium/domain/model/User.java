package br.edu.ufrn.foodium.domain.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private Long id;
    private String name;
    private List<Topic> topics;

    public User (String name, List<Topic> topics) {
        this.name = name;
        this.topics = topics;
    }
}