package com.library.bookrental.controller;

import com.library.bookrental.domain.Book;
import com.library.bookrental.domain.SpecimenOfBook;
import com.library.bookrental.domain.constans.SpecimenStatus;
import com.library.bookrental.service.BookDbService;
import com.library.bookrental.service.SpecimenOfBookDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library")
public class BookController {
    @Autowired
    BookDbService bookDbService;

    @Autowired
    SpecimenOfBookDbService specimenOfBookDbService;


    @RequestMapping(method = RequestMethod.POST, value = "/book")
    public Book addNewBook(@RequestBody Book book) {
        return bookDbService.addNewBook(book.getTitle(), book.getAuthor(), book.getYearOfPublishing());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/book/specimen/{bookId}/{specimenOfBookId}")
    public SpecimenOfBook addNewSpecimenOfBook(@PathVariable long bookId, @PathVariable long specimenOfBookId) {
        return specimenOfBookDbService.addNewSpecimenOfBook(bookId, specimenOfBookId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/book/specimen")
    public SpecimenOfBook updateSpecimenOfBookStatus(@RequestBody SpecimenOfBook specimenOfBook) {
        return specimenOfBookDbService.updateSpecimenOfBookStatus(specimenOfBook.getId(), specimenOfBook.getSpecimenStatus());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/book/specimen/number/{bookId}")
    public int findNumberOfSpecimensOfBookAvailable(@PathVariable long bookId) {
        return specimenOfBookDbService.findNumberOfSpecimensOfBookAvailable(bookId);
    }
}
