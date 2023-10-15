package com.workshop.milkshake.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workshop.milkshake.entity.recipe;

@Repository
public interface RecipeRepository extends JpaRepository<recipe, Long> {
    
}
