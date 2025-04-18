package com.think.rest_demo.mapper;

import com.think.rest_demo.dto.BookDto;
import com.think.rest_demo.model.Book;

public class BookMapper {

    public static BookDto toDto(Book book)
    {
        BookDto bookDto = new BookDto(book.getBookid(), book.getTitle(), book.getGenre(),
                book.getPrice(),book.getDescription(),book.isAvailable(), book.getVendor());
        return bookDto;
    }

    public static Book toEntity(BookDto bookDto)
    {
        Book book = new Book(bookDto.bookid(), bookDto.title(), bookDto.price(),
                bookDto.genre(), bookDto.description(),bookDto.available());
        return book;
    }

}
