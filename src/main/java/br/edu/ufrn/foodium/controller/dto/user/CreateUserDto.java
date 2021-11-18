package br.edu.ufrn.foodium.controller.dto.user;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserDto {

     @NotEmpty(message = "Informe um nome")
     private String name;

     @NotEmpty(message = "Informe um username")
     private String userName;

     @NotEmpty(message = "Informe uma senha")
     private String password;

     private List<Long> tagsIds;
}
