package br.edu.ufrn.foodium.controller.dto.post;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreatePostDto {
     private Long userId;

     private String imageUrl;

     private String content;

     private List<Long> tagsIds;
}
