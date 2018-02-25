package com.library.bookrental.service;

import com.library.bookrental.domain.Reader;
import com.library.bookrental.domain.dao.ReaderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Transactional
@Repository
public class ReaderDbService {
    @Autowired
    ReaderDao readerDao;

    public Reader addNewReader(String firstName, String lastName) {
        Reader reader = new Reader(firstName, lastName);
        readerDao.save(reader);
        return reader;
    }
}
