package br.edu.ufrn.foodium.controller.dto.user;

import br.edu.ufrn.foodium.domain.model.Tag;
import br.edu.ufrn.foodium.domain.model.User;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResponseUserDto {

    public ResponseUserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.userName = user.getUserName();
        this.tags = user.getTags();
    }

    private Long id;

    private String name;

    private String userName;

    private List<Tag> tags;
}
