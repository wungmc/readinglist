/*
 * Copyright (C), 2011-2018.
 */
package com.wung.controller;

import com.wung.model.Book;
import com.wung.repository.ReadingListRepository;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author wung 2018/9/28.
 */
@Slf4j
@Controller
@RequestMapping("/")
public class ReadingListController {
	
	@Autowired
	private ReadingListRepository readingListRepository;
	
	
	@GetMapping("/{reader}")
	public String readerBoooks(@PathVariable("reader") String reader, Model model) {
		List<Book> bookList = readingListRepository.findByReader(reader);
		if (bookList != null) {
			model.addAttribute("books", bookList);
		}
		return "readingList";
	}
	
	
	@PostMapping("/{reader}")
	public String addToReadingList(@PathVariable("reader") String reader, Book book) {
		book.setReader(reader);
		readingListRepository.save(book);
		return "redirect:/{reader}";
	}
	
	
}
