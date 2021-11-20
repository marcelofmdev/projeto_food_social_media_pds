package br.edu.ufrn.foodium.controller;

import br.edu.ufrn.foodium.controller.dto.ErrorMessageDto;
import br.edu.ufrn.foodium.domain.exception.BusinessException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @NonNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException exception,
        @NonNull HttpHeaders headers,
        @NonNull HttpStatus status,
        WebRequest request
    ) {
        List<String> errors = new ArrayList<>();

        exception.getBindingResult().getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));

        ErrorMessageDto errorMessageDto = new ErrorMessageDto(
            HttpStatus.BAD_REQUEST.value(),
            new Date(),
            errors,
            request.getDescription(false));

        return new ResponseEntity<>(errorMessageDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorMessageDto> businessException(BusinessException exception, WebRequest request) {
        ErrorMessageDto errorMessageDto = new ErrorMessageDto(
            exception.getStatusCode() != null
                ? exception.getStatusCode()
                : HttpStatus.INTERNAL_SERVER_ERROR.value(),
            new Date(),
            Collections.singletonList(exception.getMessage()),
            request.getDescription(false));

        return new ResponseEntity<>(errorMessageDto, HttpStatus.valueOf(errorMessageDto.getStatusCode()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessageDto globalExceptionHandler(Exception ex, WebRequest request) {
        return new ErrorMessageDto(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            new Date(),
            Collections.singletonList(ex.getMessage()),
            request.getDescription(false));
    }
}