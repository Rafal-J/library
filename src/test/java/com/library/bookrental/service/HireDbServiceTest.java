package com.library.bookrental.service;

import com.library.bookrental.domain.Book;
import com.library.bookrental.domain.Hire;
import com.library.bookrental.domain.Reader;
import com.library.bookrental.domain.SpecimenOfBook;
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
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest

public class HireDbServiceTest {
    @Autowired
    ReaderDao readerDao;

    @Autowired
    BookDao bookDao;

    @Autowired
    HireDao hireDao;

    @Autowired
    SpecimenOfBookDao specimenOfBookDao;

    @Autowired
    HireDbService hireDbService;

    @Autowired
    SpecimenOfBookDbService specimenOfBookDbService;

    @Test
    public void addNewHire() throws Exception {
        //Given
        Reader reader = new Reader("Piotr", "Konopka");
        readerDao.save(reader);
        Book book = new Book("Sztuka Wolności", "Bernadette McDonald", 2017);
        bookDao.save(book);
        SpecimenOfBook specimenOfBook1 = new SpecimenOfBook(773L, SpecimenStatus.W_OBIEGU, book);
        SpecimenOfBook specimenOfBook2 = new SpecimenOfBook(774L, SpecimenStatus.NOWA, book);
        specimenOfBookDao.save(specimenOfBook1);
        specimenOfBookDao.save(specimenOfBook2);

        //When
        SpecimenOfBook specimenAvailable = specimenOfBookDao.findSpecimensOfBookAvailable(book.getId()).get(0);
        Hire hire1 = hireDbService.bookHire(reader.getId(), book.getId());
        Hire hire2 = hireDbService.bookHire(reader.getId(), book.getId() + 2);
        Hire hire3 = hireDbService.bookHire(reader.getId() - 3, book.getId() + 2);

        //Then
        Assert.assertEquals(774L, hire1.getSpecimenOfBook().getId());
        Assert.assertEquals("Konopka", readerDao.findOne(hire1.getReader().getId()).getLastName());
        Assert.assertEquals(null, hire2);
        Assert.assertEquals(null, hire3);

        bookDao.delete(book.getId());
        readerDao.delete(reader.getId());
    }

    @Test
    public void bookReturn() throws Exception {
        //Given
        Reader reader = new Reader("Igor", "Bykowski");
        readerDao.save(reader);
        Book book = new Book("Zrób to sam", "Adam Słodowy", 1965);
        bookDao.save(book);
        SpecimenOfBook specimenOfBook1 = new SpecimenOfBook(54L, SpecimenStatus.W_OBIEGU, book);
        SpecimenOfBook specimenOfBook2 = new SpecimenOfBook(55L, SpecimenStatus.NOWA, book);
        specimenOfBookDao.save(specimenOfBook1);
        specimenOfBookDao.save(specimenOfBook2);
        SpecimenOfBook specimenAvailable = specimenOfBookDao.findSpecimensOfBookAvailable(book.getId()).get(0);
        Hire hire1 = hireDbService.bookHire(reader.getId(), book.getId());

        //When
        hire1 = hireDbService.bookReturn(reader.getId(), specimenOfBook2.getId());
        Hire hire2 = hireDbService.bookReturn(reader.getId(), -4L);
        Hire hire3 = hireDbService.bookReturn(-4L, specimenOfBook2.getId());

        //Then
        Assert.assertNotEquals(null, hire1.getReturnDate());
        Assert.assertEquals(SpecimenStatus.ZWRÓCONA, specimenOfBookDao.findOne(hire1.getSpecimenOfBook().getId()).getSpecimenStatus());
        Assert.assertEquals(null, hire2);
        Assert.assertEquals(null, hire3);

        bookDao.delete(book.getId());
        readerDao.delete(reader.getId());
    }
}