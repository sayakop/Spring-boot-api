package com.think.rest_demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.think.rest_demo.model.Book;
import com.think.rest_demo.response.BookResponseHandler;
import com.think.rest_demo.service.Book.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.persistence.EntityNotFoundException;


@RestController
@RequestMapping("/books")
public class BookController {

    private static final Logger bookLogInfo = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/welcome")
    public ResponseEntity<String> welcomeMessage()
    {
        return new ResponseEntity<>("Welcome to the Book Details", HttpStatus.OK);
    }
    
    //Get All Books from DB

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<Book> getAllBooks()  
    {
        return bookService.getAllBooks();
    }

    // Get a Particular Book from DB
    @GetMapping("{bookid}")
     @Operation(
    summary = "Get Book by ID",
    description = "Provide a book ID to retrieve the book details",
    responses = {
        @ApiResponse(responseCode = "200", description = "Successful operation"),
        @ApiResponse(responseCode = "404", description = "Book not found")
    }
)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> getBook(@PathVariable Long bookid)
    {
        bookLogInfo.info("User Info Logging is Enabled");
        bookLogInfo.debug("User Debug Logging is enabled");
        return BookResponseHandler.responseBuilder
        ("Requested Book details are here", HttpStatus.OK, bookService.getBook(bookid));
    }
 
    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> addBooks(@RequestBody Book book) {
        bookService.addBooks(book);
        return BookResponseHandler.responseBuilder("Book Added Succesfully", HttpStatus.CREATED, book);
    }

    @PutMapping("/{bookid}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> updateBooks(@PathVariable Long bookid,@RequestBody Book book)
    {
        bookService.updateBooks(bookid,book);
        return BookResponseHandler.responseBuilder("Book Details Updated Successfully", HttpStatus.OK, book);
    }

    @PutMapping("/{bookid}/assignvendor")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> assignVendorBook(
        @PathVariable Long bookid,@RequestParam String vendorId) {

            Book updatedBook = bookService.assignVendorBook(bookid, vendorId); // No longer returns Object
            return BookResponseHandler.responseBuilder(
                "Vendor assigned to book successfully",
                HttpStatus.OK,
                updatedBook // Return the updated book
        );
    }

    @ExceptionHandler(EntityNotFoundException.class) // Handle the exception
    public ResponseEntity<Object> NotFoundBookException(EntityNotFoundException ex) {
        return BookResponseHandler.responseBuilder(
                ex.getMessage(), // Get the message from the exception
                HttpStatus.NOT_FOUND,
                null // No data needed in the body for this error
        );
    }

    @DeleteMapping("/{bookid}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String>  deleteBook(@PathVariable Long bookid)
    {
        boolean bookIsDeleted = bookService.deleteBook(bookid);
        if(bookIsDeleted)
        {
            return new ResponseEntity<>("Book Deleted Successfully",HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("Book or Vendor Not Found",HttpStatus.NOT_FOUND);

        }
        
    }
}
