package hh.swd.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd.Bookstore.domain.Book;
import hh.swd.Bookstore.domain.BookRepository;
import hh.swd.Bookstore.domain.CRepository;
import hh.swd.Bookstore.domain.Category;


@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);  // uusi loggeriattribuutti
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
		
		@Bean
		public CommandLineRunner bookDemo(BookRepository bookRepository, CRepository crepository) { 
			return (args) -> {
				log.info("save a couple of category");
				crepository.save(new Category("Horror"));
				crepository.save(new Category("Kids"));
				log.info("save a couple of books");
				bookRepository.save(new Book(null, "Matti Mainio", "Kun nukut pommiin", 2019, 23281-9, "12.10", crepository.findByName("Horror").get(0)));
				bookRepository.save(new Book(null, "Igor Velinen", "Elämäni Ihaana", 2010, 218742-4, "15,20"));	
				
				log.info("fetch all books");
				for (Book book : bookRepository.findAll()) {
					log.info(book.toString());
				}

			};
	}

}
