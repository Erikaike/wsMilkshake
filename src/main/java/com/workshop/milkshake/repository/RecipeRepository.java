package com.workshop.milkshake.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workshop.milkshake.entity.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    
}
