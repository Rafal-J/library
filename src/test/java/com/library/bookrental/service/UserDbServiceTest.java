package com.library.bookrental.service;

import com.library.bookrental.domain.Reader;
import com.library.bookrental.domain.dao.ReaderDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDbServiceTest {
    @Autowired
    ReaderDbService readerDbService;

    @Autowired
    ReaderDao readerDao;

    @Test
    public void addNewReader() throws Exception {
        //Given @ When
        Reader reader = readerDbService.addNewReader("Wiktor", "Zborowski");

        //Then
        Assert.assertTrue(reader.getId() > -1L);
        Assert.assertTrue(!reader.getCreated().equals(null));

        readerDao.delete(reader);
    }

}