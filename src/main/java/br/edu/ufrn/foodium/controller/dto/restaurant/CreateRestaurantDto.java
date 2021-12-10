package br.edu.ufrn.foodium.controller.dto.restaurant;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateRestaurantDto {
    @NotEmpty(message = "Informe um nome")
    private String name;

    private String description;

    private String logo;

    private List<Long> tagsIds;
}
