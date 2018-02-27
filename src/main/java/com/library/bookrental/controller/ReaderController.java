package com.library.bookrental.controller;

import com.library.bookrental.domain.Hire;
import com.library.bookrental.domain.Reader;
import com.library.bookrental.service.HireDbService;
import com.library.bookrental.service.ReaderDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/library")
public class ReaderController {
    @Autowired
    private ReaderDbService readerDbService;

    @Autowired
    private HireDbService hireDbService;

    @RequestMapping(method = RequestMethod.POST, value = "/reader")
    public Reader addNewReader(@RequestBody Reader reader) {
        return readerDbService.addNewReader(reader.getFirstName(), reader.getLastName());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/hire/{readerId}/{bookId}")
        public Hire bookHire(@PathVariable long readerId, @PathVariable long bookId) {
            return hireDbService.bookHire(readerId, bookId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/return/{readerId}/{specimenOfBookId}")
    public Hire bookReturn(@PathVariable long readerId, @PathVariable long specimenOfBookId) {
        return hireDbService.bookReturn(readerId, specimenOfBookId);
    }
}
