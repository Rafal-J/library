package com.library.bookrental.domain;

import com.library.bookrental.domain.dao.HireDao;
import com.library.bookrental.domain.dao.ReaderDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReaderTest {
    @Autowired
    ReaderDao readerDao;

    @Test
    public void readerTest () {
        //Given
        Reader reader = new Reader("Rafał", "Janus");

        //When
        readerDao.save(reader);

        //Then
        Assert.assertTrue(!readerDao.findOne(reader.getId()).equals(null));

        readerDao.delete(reader.getId());
    }
}