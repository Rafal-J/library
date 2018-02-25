package com.library.bookrental.domain.dao;

import com.library.bookrental.domain.Book;
import com.library.bookrental.domain.Hire;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.time.LocalDate;


@Transactional
@Repository
public interface HireDao extends CrudRepository<Hire, Long>{
    @Query(nativeQuery = true)
    Hire wasHired(@Param("READERID") long readerId, @Param("SPECIMENOFBOOKID") long specimenOfBookId);
}
