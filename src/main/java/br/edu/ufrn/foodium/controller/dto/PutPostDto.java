package br.edu.ufrn.foodium.controller.dto;

import java.time.LocalDate;
import java.util.List;

public class PutPostDto {
    public Long user_id;

    public Long post_id;

    public String imageUrl;

    public String content;

    public LocalDate date = LocalDate.now();

    public List<Long> tags_ids;
}
