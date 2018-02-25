package com.library.bookrental.service;

import com.library.bookrental.domain.Hire;
import com.library.bookrental.domain.SpecimenOfBook;
import com.library.bookrental.domain.constans.SpecimenStatus;
import com.library.bookrental.domain.dao.HireDao;
import com.library.bookrental.domain.dao.ReaderDao;
import com.library.bookrental.domain.dao.SpecimenOfBookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public class HireDbService {
    @Autowired
    ReaderDao readerDao;

    @Autowired
    SpecimenOfBookDao specimenOfBookDao;

    @Autowired
    SpecimenOfBookDbService specimenOfBookDbService;

    @Autowired
    HireDao hireDao;

    public Hire bookHire(long readerId, long bookId) {
        if(specimenOfBookDbService.findNumberOfSpecimensOfBookAvailable(bookId) > 0 && readerDao.findOne(readerId) != null) {
            List<SpecimenOfBook> specimensOfBook = specimenOfBookDao.findSpecimensOfBookAvailable(bookId);
            Hire hire = new Hire(readerDao.findOne(readerId), specimensOfBook.get(0));
            hireDao.save(hire);
            specimenOfBookDbService.updateSpecimenOfBookStatus(specimensOfBook.get(0).getId(), SpecimenStatus.W_OBIEGU);
            return hire;
        }
        return null;
    }

    public Hire bookReturn(long readerId, long specimenOfBookId) {
        Hire hire = hireDao.wasHired(readerId, specimenOfBookId);
        if(hire != null) {
            hire.setReturnDate(LocalDate.now());
            specimenOfBookDao.findOne(specimenOfBookId).setSpecimenStatus(SpecimenStatus.ZWRÓCONA);
            return hire;
        }
        return null;
    }
}