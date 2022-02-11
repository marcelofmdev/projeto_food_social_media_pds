package br.edu.ufrn.foodium.framework.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ErrorMessageDto {
    private Integer statusCode;
    private Date timestamp;
    private List<String> messages;
    private String description;
}