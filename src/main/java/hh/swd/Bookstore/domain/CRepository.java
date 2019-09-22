package hh.swd.Bookstore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CRepository {
	


		public interface CategoryRepository extends CrudRepository<Category, Long> {

		    List<Category> findByName(String name);
		    
		}

		
	}


