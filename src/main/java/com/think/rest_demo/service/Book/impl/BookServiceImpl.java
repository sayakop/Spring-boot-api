package com.think.rest_demo.service.Book.impl;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.think.rest_demo.exception.BookException.NotFoundBookException;
import com.think.rest_demo.model.Book;
import com.think.rest_demo.model.Vendor;
import com.think.rest_demo.repository.BookRepository;
import com.think.rest_demo.repository.VendorRepository;
import com.think.rest_demo.service.Book.BookService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final VendorRepository vendorRepository;

    public BookServiceImpl(BookRepository bookRepository, VendorRepository vendorRepository) {
        this.bookRepository = bookRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public List<Book> getAllBooks()
    {
        return bookRepository.findAll();
    }

    @Override
    public Book addBooks(Book book)
    {
       return bookRepository.save(book);
    }

    @Override
    public Book getBook(Long bookid) {
            return bookRepository.findById(bookid)
                    .orElseThrow(() -> new NotFoundBookException("Requested Book Not Found"));
        }
        
    @Override
    public boolean deleteBook(Long bookid)
    {
        Optional<Book> book = bookRepository.findById(bookid);
        if (book.isPresent()) {
            bookRepository.delete(book.get());
            System.out.println("Book Deleted Successfully");
            return true; // book was found and deleted
        } else {
            System.out.println("Book Not found");
            return false; // book was not found
        }
    }

    @Override
    public Book updateBooks(Long bookid,Book book) {
        Optional<Book> optionalBook = bookRepository.findById(bookid);

        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();
            existingBook.setTitle(book.getTitle());
            existingBook.setGenre(book.getGenre());
            existingBook.setDescription(book.getDescription());
            existingBook.setPrice(book.getPrice());
            existingBook.setAvailable(book.isAvailable());
            return bookRepository.save(existingBook);
        } else {
            return bookRepository.save(book);
        }
    }

    @Override
    public Book assignVendorBook(Long bookid, String vendorId) {
        Optional<Book> bookOpt = bookRepository.findById(bookid);
        Optional<Vendor> vendorOpt = vendorRepository.findById(vendorId);

        if (bookOpt.isEmpty() && vendorOpt.isEmpty()) {
            throw new EntityNotFoundException("Book and Vendor Not Found");
        } else if (bookOpt.isEmpty()) {
            throw new EntityNotFoundException("Book Not Found");
        } else if (vendorOpt.isEmpty()) {
            throw new EntityNotFoundException("Vendor Not Found");
        }

        Book book = bookOpt.get();
        Vendor vendor = vendorOpt.get();
        book.setVendor(vendor);
        return bookRepository.save(book);
        }
    }
