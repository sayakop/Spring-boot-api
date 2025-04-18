package com.think.rest_demo.mapper;

import com.think.rest_demo.dto.BookDto;
import com.think.rest_demo.dto.CreateBookRecord;
import com.think.rest_demo.model.Book;
import com.think.rest_demo.model.Vendor;

public record BookMapper() {

    public static Book fromCreateDto(CreateBookRecord dto, Vendor vendor) {
        Book book = new Book();
        book.setBookid(dto.bookid());
        book.setTitle(dto.title());
        book.setGenre(dto.genre());
        book.setDescription(dto.description());
        book.setPrice(dto.price());
        book.setAvailable(dto.available());
        book.setVendor(vendor);
        return book;
    }

    public static BookDto toDto(Book book) {
        return new BookDto
        (book.getBookid(),
         book.getTitle(),
         book.getGenre(),
         book.getDescription(),
         book.getPrice(),
         book.isAvailable(),
         book.getVendor() != null ? book.getVendor().getVendorName() : null
         );
    }

}
