package com.generation.lojaGames.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.lojaGames.model.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    public List<Category> findAllByGenderContainingIgnoreCase(@Param("gender") String gender);
    public List<Category> findAllByPlataformContainingIgnoreCase(@Param("plataform") String plataform);

}
