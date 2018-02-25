package com.library.bookrental.domain;

import com.library.bookrental.domain.dao.BookDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookTestSuite {
    @Autowired
    BookDao bookDao;

    @Test
    public void bookTestSuite() throws Exception {
        //Given
        Book book = new Book("Mój pionowy świat", "Jerzy Kukuczka", 1989);

        //When
        bookDao.save(book);

        //Then
        Assert.assertEquals(1989, bookDao.findOne(book.getId()).getYearOfPublishing());

        bookDao.delete(book);
    }

    @Test
    public void findBookTest() throws Exception {
        //Given
        Book book = new Book("Mój pionowy świat", "Jerzy Kukuczka", 1989);

        //When
        bookDao.save(book);
        String title = bookDao.findBookByTitleAuthorAndYear("Mój pionowy świat", "Jerzy Kukuczka", 1989).getTitle();
        long book_id = bookDao.findBookByTitleAuthorAndYear("Mój pionowy świat", "Jerzy Kukuczka", 1989).getId();

        //Then
        Assert.assertEquals("Mój pionowy świat", title);
        Assert.assertEquals(book.getId(), book_id);

        bookDao.delete(book);
    }
}