package com.generation.lojaGames.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;


@Entity
@Table(name = "tb_Category")
public class Category {

    // Identificador Único da Tabela
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Descrição do Gênero
    @NotBlank
    @Size(min = 1, max = 100, message = "O atributo Gênero deve conter no mínimo 01 e no máximo 100 caracteres")
    private String gender;

    // Descrição da Plataforma
    @NotBlank
    @Size(min = 1, max = 100, message = "O atributo Gênero deve conter no mínimo 01 e no máximo 100 caracteres")
    private String plataform;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Product> product;


    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPlataform() {
        return plataform;
    }

    public void setPlataform(String plataform) {
        this.plataform = plataform;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
}
