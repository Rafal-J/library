package com.library.bookrental.service;

import com.library.bookrental.domain.Book;
import com.library.bookrental.domain.SpecimenOfBook;
import com.library.bookrental.domain.constans.SpecimenStatus;
import com.library.bookrental.domain.dao.BookDao;
import com.library.bookrental.domain.dao.SpecimenOfBookDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpecimenOfBookDbServiceTest {

    @Autowired
    BookDao bookDao;

    @Autowired
    SpecimenOfBookDao specimenOfBookDao;

    @Autowired
    SpecimenOfBookDbService specimenOfBookDbService;

    @Test
    public void addNewSpecimenOfBook() throws Exception {
        //Given
        Book book = new Book("Czarnoksiężnik z archipelagu", "Ursula Le Guin", 1985);
        bookDao.save(book);

        //When
        SpecimenOfBook specimenOfBook1 = specimenOfBookDbService.addNewSpecimenOfBook(book.getId(), 1234L);
        SpecimenOfBook specimenOfBook2 = specimenOfBookDbService.addNewSpecimenOfBook(book.getId(), 1234L);
        SpecimenOfBook specimenOfBook3 = specimenOfBookDbService.addNewSpecimenOfBook(book.getId() + 5, 2234L);

        System.out.println(specimenOfBook1.getId());
        //Then
        Assert.assertNotEquals(null, specimenOfBook1);
        Assert.assertEquals(null, specimenOfBook2);
        Assert.assertEquals(null, specimenOfBook3);

        specimenOfBookDao.delete(specimenOfBook1.getId());
        bookDao.delete(book);
    }

    @Test
    public void updateSpecimenOfBookStatus() throws Exception {
        //Given
        Book book = new Book("Władca pierścieni", "JRR Tolkien", 1938);
        bookDao.save(book);
        SpecimenOfBook specimenOfBook = specimenOfBookDbService.addNewSpecimenOfBook(book.getId(), 4321L);
        specimenOfBookDao.save(specimenOfBook);

        //When
        specimenOfBook = specimenOfBookDbService.updateSpecimenOfBookStatus(specimenOfBook.getId(), SpecimenStatus.W_OBIEGU);

        //Then
        Assert.assertEquals(SpecimenStatus.W_OBIEGU, specimenOfBook.getSpecimenStatus());

        specimenOfBookDao.delete(specimenOfBook.getId());
        bookDao.delete(book);
    }
    @Test
    public void findNumberOfSpecimensOfBookAvailable() throws Exception {
        //Given
        Book book = new Book("Na jagody", "Jan Brzechwa", 1958);
        bookDao.save(book);
        SpecimenOfBook specimenOfBook1 = specimenOfBookDbService.addNewSpecimenOfBook(book.getId(), 521L);
        SpecimenOfBook specimenOfBook2 = specimenOfBookDbService.addNewSpecimenOfBook(book.getId(), 522L);
        SpecimenOfBook specimenOfBook3 = specimenOfBookDbService.addNewSpecimenOfBook(book.getId(), 523L);
        SpecimenOfBook specimenOfBook4 = specimenOfBookDbService.addNewSpecimenOfBook(book.getId(), 524L);

        specimenOfBookDao.save(specimenOfBook1);
        specimenOfBookDao.save(specimenOfBook2);
        specimenOfBookDao.save(specimenOfBook3);
        specimenOfBookDao.save(specimenOfBook4);

        //When
        System.out.println(book.getId());
        int availableForHire = specimenOfBookDbService.findNumberOfSpecimensOfBookAvailable(book.getId());
        System.out.println(availableForHire);

        //Then
        Assert.assertTrue(availableForHire == 4);

        specimenOfBookDao.delete(specimenOfBook1.getId());
        specimenOfBookDao.delete(specimenOfBook2.getId());
        specimenOfBookDao.delete(specimenOfBook3.getId());
        specimenOfBookDao.delete(specimenOfBook4.getId());

        bookDao.delete(book);
    }
}