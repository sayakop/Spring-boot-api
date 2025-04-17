package com.think.rest_demo.service.Book;

import java.util.List;

import com.think.rest_demo.model.Book;

public interface BookService {

    public Book addBooks(Book book);
    public boolean deleteBook(Long bookid);
    public Book getBook(Long bookid);
    public List<Book> getAllBooks();
    public Book updateBooks(Long bookid,Book book);
    public Book assignVendorBook(Long bookid, String vendorId);


}
