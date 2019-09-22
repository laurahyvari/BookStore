package hh.swd.Bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import hh.swd.Bookstore.domain.Book;
import hh.swd.Bookstore.domain.BookRepository;
import hh.swd.Bookstore.domain.CRepository;


@Controller

public class BookController {
	
	@Autowired
	BookRepository bookRepository; 
	
	@Autowired
	CRepository crepository;
	
	
	// kirjalistaus
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String getBooks(Model model) {
			List<Book> books =  (List<Book>) bookRepository.findAll(); // haeta tietokannasta kirjat
			model.addAttribute("books", books); // välitetään kirjalista templatelle model-olion avulla
			return "booklist"; // DispatherServlet saa tämän template-nimen ja kutsuu seuraavaksi booklist.html-templatea,
								// joka prosessoidaan palvelimella
	}

	// tyhjän kirjalomakkeen muodostaminen
	@RequestMapping(value = "/newbook", method = RequestMethod.GET)
	public String getNewBookForm(Model model) {
		model.addAttribute("book", new Book()); // "tyhjä" kirja-olio
		model.addAttribute("categories", CRepository.findAll());
		return "addBook";
	}

	// kirjalomakkeella syötettyjen tietojen vastaanotto ja tallennus
	@RequestMapping(value = "/savebook", method = RequestMethod.POST)
	public String saveBook(@ModelAttribute Book book) {
		// talletetaan yhden kirjan tiedot tietokantaan
		bookRepository.save(book);
		return "redirect:/books";
	}

	// kirjan poisto
	@RequestMapping(value = "/deletebook/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId) {
		bookRepository.deleteById(bookId);
		return "redirect:../books";
	}
	
	

}
