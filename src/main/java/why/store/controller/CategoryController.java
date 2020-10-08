package why.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import why.store.entity.Category;
import why.store.repo.CategoryRepo;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryRepo categoryRepo;

    @GetMapping("/")
    public List<Category> getCategories() {
        return categoryRepo.findAll();
    }

    @PostMapping("/")
    public Category addCategory(@Validated @RequestBody Category request){
        return categoryRepo.save(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable(value = "id") Long id,
                                                 @Validated @RequestBody Category request){
        Category category = categoryRepo.getOne(id);
        if (category == null) return ResponseEntity.notFound().build();
        Category updatedCategory = categoryRepo.save(request);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable(value = "id") Long id){
        Category category = categoryRepo.getOne(id);
        if (category == null) return "category with id " + id + "not found";
        categoryRepo.deleteById(id);
        return "category with id " + id + " removed successfully";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable(value = "id") Long id) {
        Category category = categoryRepo.getOne(id);
        if (category == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(category);
    }

    @GetMapping("/findby/name")
    public List<Category> sortCategory(@RequestParam(value = "name") String name){
        return categoryRepo.findByName(name);
    }
}
