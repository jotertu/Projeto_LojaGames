package com.generation.lojaGames.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_Products")
public class Product {

    // Identificador Único da Tabela
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome do Produto
    @NotNull
    @Size(min = 1, max = 100, message = "O atributo nome deve conter no mínimo 01 e no máximo 100 caracteres")
    private String name;

    // Descrição do Produto
    @NotNull
    @Size(min = 5, max = 1000, message = "O atributo descrição deve conter no mínimo 05 e no máximo 1000 caracteres")
    private String description;

    // Imagem do Produto
    @NotBlank(message = "O atributo Imagem não pode ser nulo ou vazio")
    private String picture;

    // Preço do Produto
    @NotNull(message = "O atributo price não pode ser nulo")
    private Double price;

    // Quantia em Estoque do Produto
    @NotNull(message = "O atributo Estoque não pode ser nulo")
    @Min(value = 0, message = "O atributo Estoque deve ser maior ou igual a zero")
    private Long stock;

    // Data e hora da Publicação do Produto / Atualização do Produto
    @UpdateTimestamp
    private LocalDateTime date;

    @ManyToOne
    @JsonIgnore
    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    // Getters and Setters
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
