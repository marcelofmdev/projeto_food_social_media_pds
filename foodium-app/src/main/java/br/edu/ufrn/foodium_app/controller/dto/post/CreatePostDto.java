package br.edu.ufrn.foodium_app.controller.dto.post;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class CreatePostDto {

     @NotNull(message = "O id do usuário é obrigatório")
     private Long userId;

     private String imageUrl;

     private String content;

     private List<Long> tagsIds;
}
