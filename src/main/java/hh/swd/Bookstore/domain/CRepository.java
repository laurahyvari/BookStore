package hh.swd.Bookstore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

	


		public interface CRepository extends CrudRepository<Category, Long> {

		    List<Category> findByName(String name);
		    
		}

		
	


