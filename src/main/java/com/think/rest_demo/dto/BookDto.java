package com.think.rest_demo.dto;

public record BookDto(
    Long bookid,
    String title,
    String genre,
    String description,
    String price,
    boolean available,
    String vendorId
) {}
