package br.edu.ufrn.bookium.controller;

import br.edu.ufrn.bookium.framework.controller.dto.ErrorMessageDto;
import br.edu.ufrn.bookium.domain.exception.BusinessException;
import br.edu.ufrn.bookium.framework.domain.exception.NotFoundException;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String DEFAULT_ERROR_MESSAGE = "Ocorreu um erro interno. Já estamos trabalhando para tentar resolvê-lo, por favor tente novamente mais tarde.";

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
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            new Date(),
            Collections.singletonList(exception.getMessage()),
            request.getDescription(false));

        return new ResponseEntity<>(errorMessageDto, HttpStatus.valueOf(errorMessageDto.getStatusCode()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessageDto> notFoundException(NotFoundException exception, WebRequest request) {
        ErrorMessageDto errorMessageDto = new ErrorMessageDto(
            HttpStatus.NOT_FOUND.value(),
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
            Collections.singletonList(DEFAULT_ERROR_MESSAGE),
            request.getDescription(false));
    }
}