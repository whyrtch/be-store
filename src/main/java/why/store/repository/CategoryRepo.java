package why.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import why.store.entity.Category;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Long> {

    List<Category> findByName(String name);
}
