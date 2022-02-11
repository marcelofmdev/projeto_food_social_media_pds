package br.edu.ufrn.foodium.controller.dto.restaurant;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class UpdateRestaurantDto {
    @NotNull(message = "O id do usuário é obrigatório")
    private Long id;

    @NotEmpty(message = "Informe um nome")
    private String name;

    private String description;

    private String logo;

    private List<Long> tagsIds;
}
