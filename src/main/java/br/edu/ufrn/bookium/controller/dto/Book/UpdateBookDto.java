package br.edu.ufrn.bookium.controller.dto.Book;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class UpdateBookDto {
    @NotNull(message = "O id da publicação é obrigatório")
    private Long id;

    @NotNull(message = "O id do usuário é obrigatório")
    private Long userId;

    private String imageUrl;

    private String content;

    private List<Long> tagsIds;
}