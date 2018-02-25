package com.library.bookrental.domain;

import com.library.bookrental.domain.constans.SpecimenStatus;
import com.library.bookrental.domain.dao.BookDao;
import com.library.bookrental.domain.dao.SpecimenOfBookDao;
import javafx.beans.binding.When;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Column;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpecimenOfBookTest {
    @Autowired
    SpecimenOfBookDao specimenOfBookDao;

    @Autowired
    BookDao bookDao;

    @Test
    public void specimenTest() {
        //Given
        Book book = new Book("Wanda", "Anna Kamińska", 2017);
        SpecimenOfBook specimenOfBook1 = new SpecimenOfBook(323L, SpecimenStatus.NOWA, book);
        SpecimenOfBook specimenOfBook2 = new SpecimenOfBook(324L, SpecimenStatus.NOWA, book);

        //When
        bookDao.save(book);
        specimenOfBookDao.save(specimenOfBook1);
        specimenOfBookDao.save(specimenOfBook2);
        book.getSpecimenOfBooks().add(specimenOfBook1);
        book.getSpecimenOfBooks().add(specimenOfBook2);

        bookDao.delete(book);
    }
}