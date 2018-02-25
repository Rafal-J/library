package com.library.bookrental.domain.dao;

import com.library.bookrental.domain.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface ReaderDao extends CrudRepository<Reader, Long>{
}
