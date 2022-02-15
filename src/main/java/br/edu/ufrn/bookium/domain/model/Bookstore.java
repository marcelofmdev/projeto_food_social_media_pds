package br.edu.ufrn.bookium.domain.model;

import br.edu.ufrn.bookium.framework.domain.model.Resource;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bookstore", schema = "public")
public class Bookstore extends Resource {

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String logo;

    @Column
    private Integer qntBooks;

    @Column
    private boolean isNew = false;
}
