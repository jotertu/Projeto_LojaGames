package com.generation.lojaGames.controller;

import com.generation.lojaGames.model.Product;
import com.generation.lojaGames.repository.CategoryRepository;
import com.generation.lojaGames.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // Método getAll()
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    // Método getById
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Método getByNome
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Product>> getByName(@PathVariable String name) {
        return ResponseEntity.ok(productRepository.findAllByNameContainingIgnoreCase(name));
    }

    // Método post
    @PostMapping
    public ResponseEntity<Product> post(@Valid @RequestBody Product product) {
        if(categoryRepository.existsById(product.getCategory().getId()))
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(productRepository.save(product));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // Método Put
    @PutMapping
    public ResponseEntity<Product> put(@Valid @RequestBody Product product) {
       if(productRepository.existsById(product.getId())) {

           if (categoryRepository.existsById(product.getCategory().getId()))
               return ResponseEntity.status(HttpStatus.OK)
                       .body(productRepository.save(product));

           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }

       return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Método Delete
    @ResponseStatus
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);

        if(product.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        productRepository.deleteById(id);
    }

}
