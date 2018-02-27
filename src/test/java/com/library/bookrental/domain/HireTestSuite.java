package com.library.bookrental.domain;

import com.library.bookrental.domain.constans.SpecimenStatus;
import com.library.bookrental.domain.dao.BookDao;
import com.library.bookrental.domain.dao.HireDao;
import com.library.bookrental.domain.dao.ReaderDao;
import com.library.bookrental.domain.dao.SpecimenOfBookDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Column;

import java.time.LocalDate;

import static org.junit.Assert.*;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class HireTestSuite {
    @Autowired
    HireDao hireDao;

    @Autowired
    ReaderDao readerDao;

    @Autowired
    SpecimenOfBookDao specimenOfBookDao;

    @Autowired
    BookDao bookDao;

    @Test
    public void hireTest() {
        //Given
        Book book = new Book("Spod przymarzniętych powiek", "Adam Bielecki", 2017);
        bookDao.save(book);
        SpecimenOfBook specimenOfBook = new SpecimenOfBook(book.getId(), SpecimenStatus.ZWRÓCONA, book);
        specimenOfBookDao.save(specimenOfBook);
        Reader reader = new Reader();
        readerDao.save(reader);
        Hire hire = new Hire(reader, specimenOfBook);

        //When
        hireDao.save(hire);

        //Then
        Assert.assertTrue(!hireDao.findOne(hire.getId()).equals(null));

        System.out.println(hire.getId());
        hireDao.delete(hire.getId());
        readerDao.delete(reader.getId());
        specimenOfBookDao.delete(specimenOfBook.getId());
        bookDao.delete(book.getId());
    }
}