package hh.swd.Bookstore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

// tietokantakäsittelyn rajapinta
public interface BookRepository extends CrudRepository<Book, Long> {
	List<Book> findByAuthor(String author);
	

}

