package br.edu.ufrn.foodium_app.domain.model;

import br.edu.ufrn.framework.domain.model.Resource;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant extends Resource {

    public Restaurant(String name, String description, String logo) {
        super();
        this.name = name;
        this.description = description;
        this.logo = logo;
    }

    private String description;

    private String logo;
}
