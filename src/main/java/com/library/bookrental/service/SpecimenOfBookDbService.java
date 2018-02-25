package com.library.bookrental.service;

import com.library.bookrental.domain.Book;
import com.library.bookrental.domain.SpecimenOfBook;
import com.library.bookrental.domain.constans.SpecimenStatus;
import com.library.bookrental.domain.dao.BookDao;
import com.library.bookrental.domain.dao.SpecimenOfBookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.Period;
import java.util.Arrays;

@Transactional
@Repository
public class SpecimenOfBookDbService {
    @Autowired
    SpecimenOfBookDao specimenOfBookDao;

    @Autowired
    BookDao bookDao;

    public SpecimenOfBook addNewSpecimenOfBook(long bookId, long specimenOfBookId) {
        Book book = bookDao.findOne(bookId);
        if (book != null) {
            if (specimenOfBookDao.findOne(specimenOfBookId) == null) {
                SpecimenOfBook specimenOfBook = new SpecimenOfBook(specimenOfBookId, SpecimenStatus.NOWA, book);
                specimenOfBookDao.save(specimenOfBook);
                return specimenOfBook;
            }
        }
        return null;
    }

    public SpecimenOfBook updateSpecimenOfBookStatus(long specimenOfBookId, SpecimenStatus specimenStatus) {
        SpecimenOfBook specimenOfBook = specimenOfBookDao.findOne(specimenOfBookId);
        if(specimenOfBook != null) {
            specimenOfBook.setSpecimenStatus(specimenStatus);
            return specimenOfBook;
        }
        return null;
    }

    public int findNumberOfSpecimensOfBookAvailable(long bookId) {
        return specimenOfBookDao.findSpecimensOfBookAvailable(bookId).size();
    }
}