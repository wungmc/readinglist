/*
 * Copyright (C), 2011-2018.
 */
package com.wung.repository;

import com.wung.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author wung 2018/9/28.
 */
public interface ReadingListRepository extends JpaRepository<Book, Long> {
	List<Book> findByReader(String reader);
	
}
