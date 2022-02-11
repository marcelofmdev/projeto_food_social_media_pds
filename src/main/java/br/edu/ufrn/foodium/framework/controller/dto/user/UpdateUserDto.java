package br.edu.ufrn.foodium.framework.controller.dto.user;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class UpdateUserDto {
    @NotNull(message = "O id do usuário é obrigatório")
    private Long id;

    @NotEmpty(message = "Informe um nome")
    private String name;

    @NotEmpty(message = "Informe um username")
    private String userName;

    private List<Long> tagsIds;
}
