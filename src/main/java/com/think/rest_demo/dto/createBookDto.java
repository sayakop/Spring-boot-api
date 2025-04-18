package com.think.rest_demo.dto;

public record CreateBookDto(
        Long bookid,
        String title,
        String genre,
        String description,
        String price,
        boolean available
) {}
