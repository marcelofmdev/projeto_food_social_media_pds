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
@Table(name = "hotel", schema = "public")
public class Hotel extends Resource {

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String logo;

    @Column(name = "loc_latitude")
    private double locLatitude;

    @Column(name = "loc_longitude")
    private double locLongitude;

    private Double distance;
}
