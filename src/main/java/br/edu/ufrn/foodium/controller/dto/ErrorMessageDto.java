package br.edu.ufrn.foodium.controller.dto;


import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ErrorMessageDto {
    private Integer statusCode;
    private Date timestamp;
    private List<String> messages;
    private String description;
}