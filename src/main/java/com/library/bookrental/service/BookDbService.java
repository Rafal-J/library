package com.library.bookrental.service;

import com.library.bookrental.domain.Book;
import com.library.bookrental.domain.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public class BookDbService {
    @Autowired
    BookDao bookDao;

    public Book addNewBook(String title, String author, int yearOfPublishing) {
        Book book = bookDao.findBookByTitleAuthorAndYear(title, author, yearOfPublishing);
        if(book == null) {
            book = new Book(title, author, yearOfPublishing);
            bookDao.save(book);
            return book;
        } else {
            return null;
        }
    }
}