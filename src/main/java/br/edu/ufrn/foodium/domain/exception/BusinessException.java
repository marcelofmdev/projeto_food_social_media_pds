package br.edu.ufrn.foodium.domain.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
