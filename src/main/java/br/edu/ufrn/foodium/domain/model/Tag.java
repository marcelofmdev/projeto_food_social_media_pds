package br.edu.ufrn.foodium.domain.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tag", schema = "public")
public class Tag {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
    private Long id;

    private String name;
}