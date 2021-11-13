package br.edu.ufrn.foodium.domain.model;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum Topic {
    JAPANESE_FOOD( "JAPANESE_FOOD", "Comida Japonesa");

    private String value;
    private String description;

    Topic(String value, String description){
        this.value = value;
        this.description = description;
    }

    public static Topic fromValue(String code) {
        return Arrays.stream(values()).filter(ods -> ods.value.equals(code)).findAny().orElse(null);
    }
}
