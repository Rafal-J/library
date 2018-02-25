package com.library.bookrental.domain.dao;

import com.library.bookrental.domain.Book;
import com.library.bookrental.domain.SpecimenOfBook;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface SpecimenOfBookDao extends CrudRepository<SpecimenOfBook, Long> {
    @Query(nativeQuery = true)
    List<SpecimenOfBook> findSpecimensOfBookAvailable(@Param("BOOKID") long bookId);
}
