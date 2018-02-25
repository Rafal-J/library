package com.library.bookrental.domain.dao;

import com.library.bookrental.domain.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Transactional
@Repository
public interface BookDao extends CrudRepository<Book, Long> {
    @Query(nativeQuery = true)
    Book findBookByTitleAuthorAndYear(@Param("TITLE") String title, @Param("AUTHOR") String author, @Param("YEAROFPUBLISHING") int yearOfPublishing);
}
