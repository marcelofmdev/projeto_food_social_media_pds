package br.edu.ufrn.foodium.framework.controller.dto.user;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

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
