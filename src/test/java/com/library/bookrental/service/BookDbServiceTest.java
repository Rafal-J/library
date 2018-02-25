package com.library.bookrental.service;

import com.library.bookrental.domain.Book;
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
public class BookDbServiceTest {
    @Autowired
    BookDbService bookDbService;

    @Autowired
    BookDao bookDao;

    @Test
    public void addNewBook() throws Exception {
        //Given @ When
        Book book1 = new Book("Ogniem i mieczem", "Henryk Sienkiewicz", 1965);
        bookDao.save(book1);
        Book book2 = bookDbService.addNewBook("Ogniem i mieczem", "Henryk Sienkiewicz", 1965);

        //Then
        Assert.assertEquals("Ogniem i mieczem", bookDao.findOne(book1.getId()).getTitle());
        Assert.assertEquals(null, book2);

        bookDao.delete(book1);
    }
}