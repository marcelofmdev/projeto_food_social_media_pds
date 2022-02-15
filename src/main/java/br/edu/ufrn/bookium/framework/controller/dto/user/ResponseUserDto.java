package br.edu.ufrn.bookium.framework.controller.dto.user;

import br.edu.ufrn.bookium.framework.domain.model.Tag;
import br.edu.ufrn.bookium.framework.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

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
