package br.edu.ufrn.bookium.controller.dto.restaurant;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
public class CreateBookstoreDto {
    @NotEmpty(message = "Informe um nome")
    private String name;

    private String description;

    private String logo;

    private List<Long> tagsIds;
}
