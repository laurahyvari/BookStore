package hh.swd.Bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd.Bookstore.domain.Book;
import hh.swd.Bookstore.domain.BookRepository;
import hh.swd.Bookstore.domain.CRepository;


@Controller

public class BookController {
	
	@Autowired
	BookRepository bookRepository; 
	
	@Autowired
	CRepository crepository;
	
	
	  @RequestMapping(value="/login")
	    public String login() {	
	        return "login";
	    }	
	  
	  // Add new book
	  
	    @RequestMapping(value = "/addbook")
	    public String addStudent(Model model){
	    	model.addAttribute("book", new Book());
	    	model.addAttribute("category", crepository.findAll());
	        return "addbook";
	    }     
	
	
	// kirjalistaus
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String getBooks(Model model) {
			List<Book> books =  (List<Book>) bookRepository.findAll(); // haeta tietokannasta kirjat
			model.addAttribute("books", books); // välitetään kirjalista templatelle model-olion avulla
			return "booklist"; // DispatherServlet saa tämän template-nimen ja kutsuu seuraavaksi booklist.html-templatea,
								// joka prosessoidaan palvelimella
	}
	
	
	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
	public String getBook(Model model) {
			List<Book> books =  (List<Book>) bookRepository.findAll(); // haeta tietokannasta kirjat
			model.addAttribute("books", books); // välitetään kirjalista templatelle model-olion avulla
			return "booklist"; }
/*
	// RESTful service to get all books
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {	
        return (List<Book>) bookRepository.findAll();
    }    

	// RESTful service to get book by id
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> getBookRest(@PathVariable("id") Long bookId) {	
    	return bookRepository.findById(bookId);
    }
    
 
*/


	// tyhjän kirjalomakkeen muodostaminen
	@RequestMapping(value = "/addbook", method = RequestMethod.GET)
	public String getNewBookForm(Model model) {
		model.addAttribute("book", new Book()); // "tyhjä" kirja-olio
		model.addAttribute("categories", crepository.findAll());
		return "addbook";
		
		  
	}

	// kirjalomakkeella syötettyjen tietojen vastaanotto ja tallennus
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBook(@ModelAttribute Book book) {
		// talletetaan yhden kirjan tiedot tietokantaan
		bookRepository.save(book);
		return "redirect:/books";
	}

	// kirjan poisto
	  @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/deletebook/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId) {
		bookRepository.deleteById(bookId);
		return "redirect:../books";
	}
	
	

}
