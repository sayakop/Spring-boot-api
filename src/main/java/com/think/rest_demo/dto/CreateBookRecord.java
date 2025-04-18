package com.think.rest_demo.dto;

public record CreateBookRecord(
        Long bookid,
        String title,
        String genre,
        String description,
        String price,
        boolean available,
        String vendorId
) {}
