package catalogodigital.rest;

import catalogodigital.model.Product;
import catalogodigital.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products/")
public class ProductRest {
    ProductService productService;

    @Autowired
    public ProductRest(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> save (@RequestBody Product s){
        try {
            Product ent = productService.save(s);
            return ResponseEntity.created(new URI("/products/"+ent.getId())).body(ent);
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        return ResponseEntity.ok(productService.findAll());

    }

    @GetMapping(value="get/{id}")
    public ResponseEntity<Optional<Product>> getById(@PathVariable("id") Long  numero){
        return ResponseEntity.ok((productService.findById(numero)));
    }

    @DeleteMapping(value="delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long  id){
        Optional<Product> productOptional = productService.findById(id);
        if (productOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
        productService.deleteById(id);
        return ResponseEntity.ok(true);
    }

}
