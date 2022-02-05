package br.edu.ufrn.framework.domain.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}