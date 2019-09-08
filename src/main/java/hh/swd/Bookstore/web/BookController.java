package hh.swd.Bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import hh.swd.Bookstore.domain.Book;

@Controller

public class BookController {
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getNewCarForm(Model model) {
		model.addAttribute("book", new Book()); 
		return "testi";
	}




}
