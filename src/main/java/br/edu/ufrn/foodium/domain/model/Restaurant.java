package br.edu.ufrn.foodium.domain.model;

import br.edu.ufrn.foodium.framework.domain.model.Resource;
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
@Table(name = "restaurant", schema = "public")
public class Restaurant extends Resource {

    public Restaurant(String name, String description, String logo) {
        super();
        this.name = name;
        this.description = description;
        this.logo = logo;
    }

    @Column
    private String description;

    @Column
    private String logo;
}
