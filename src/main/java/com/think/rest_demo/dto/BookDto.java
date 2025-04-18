package com.think.rest_demo.dto;

import com.think.rest_demo.model.Vendor;

public record BookDto(
    Long bookid, 
    String title, 
    String genre, 
    String price, 
    String description,
     boolean available, 
     Vendor vendor) {
    
}
