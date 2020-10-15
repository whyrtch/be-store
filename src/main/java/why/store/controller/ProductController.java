package why.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import why.store.entity.Category;
import why.store.entity.Product;
import why.store.repository.CategoryRepo;
import why.store.repository.ProductRepo;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    CategoryRepo categoryRepo;

    @GetMapping("/")
    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    @PostMapping("/")
    public Product addProduct(@Validated @RequestBody Product request){
        return productRepo.save(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Long id,
                                                 @Validated @RequestBody Product request){
        if (productRepo.getOne(id).getName() != null) {
            Product updatedProduct = productRepo.save(request);
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable(value = "id") Long id){
        Product product = productRepo.getOne(id);
       if (product == null) return "product with id " + id + "not found";
       productRepo.deleteById(id);
       return "product with id " + id + " removed successfully";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long id) {
        Product product = productRepo.getOne(id);
        if (product == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(product);
    }

    @GetMapping("/findby/name")
    public List<Product> sortProduct(@RequestParam(value = "name") String name){
        return productRepo.findByName(name);
    }

    @GetMapping("/findby/category")
    public List<Product> sortProduct(@RequestParam(value = "category") Long categoryId){
        Category category = categoryRepo.getOne(categoryId);
        return productRepo.findByCategory(category);
    }

}
