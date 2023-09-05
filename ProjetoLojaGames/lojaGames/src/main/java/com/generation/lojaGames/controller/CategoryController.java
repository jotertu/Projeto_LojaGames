package com.generation.lojaGames.controller;

import com.generation.lojaGames.model.Category;
import com.generation.lojaGames.repository.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    // Método getAll()
    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        return ResponseEntity.ok(categoryRepository.findAll());
    }

    // Método getById
    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id) {
        return categoryRepository.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Método getByGender
    @GetMapping("/gender/{gender}")
    public ResponseEntity<List<Category>> getByGender(@PathVariable String gender) {
        return ResponseEntity.ok(categoryRepository.findAllByGenderContainingIgnoreCase(gender));
    }

    // Método getByPlataform
    @GetMapping("/plataform/{plataform}")
    public ResponseEntity<List<Category>> getByPlataform(@PathVariable String plataform) {
        return ResponseEntity.ok(categoryRepository.findAllByPlataformContainingIgnoreCase(plataform));
    }

    // Método Post
    @PostMapping
    public ResponseEntity<Category> post(@Valid @RequestBody Category category) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryRepository.save(category));
    }

    // Método Put
    @PutMapping
    private ResponseEntity<Category> put(@Valid @RequestBody Category category) {
        return  categoryRepository.findById(category.getId())
                .map(response -> ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryRepository.save(category)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Método Delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Category> category = categoryRepository.findById(id);

        if (category.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        categoryRepository.deleteById(id);
    }

}
