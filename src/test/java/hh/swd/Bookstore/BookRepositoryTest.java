package hh.swd.Bookstore;

import static org.junit.Assert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd.Bookstore.domain.Book;
import hh.swd.Bookstore.domain.BookRepository;
import hh.swd.Bookstore.domain.Category;

@RunWith(SpringRunner.class)
@DataJpaTest

public class BookRepositoryTest {

	@Autowired
	private BookRepository repository;
	
	@Test
	public void findByNameShouldReturnAuthor() {
		
		List<Book>book = repository.findByAuthor("Igor Koiranen");
		assertThat(book).hasSize(1);
		assertThat(book.get(0).getAuthor()).isEqualTo("Igor");
		
	}
	
	@Test
	public void createNewBook() {
		
		Book book = new Book (null, "Vintti Koira", "Clippers Night", 1999, 218932-8, "29,10", new Category("Scifi"));
		
	}
	
	@Test
	public void deleteBook() {
		List<Book>book = repository.deleteById();
		
	}
	
}
