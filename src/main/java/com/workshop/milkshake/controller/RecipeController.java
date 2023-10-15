package com.workshop.milkshake.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workshop.milkshake.entity.recipe;
import com.workshop.milkshake.repository.RecipeRepository;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    RecipeRepository recipeRepository;

    @GetMapping()
    public List<recipe> index(){
        return recipeRepository.findAll();
    }

    @GetMapping("/{id}")
    public recipe show(@PathVariable Long id){
        return recipeRepository.findById(id).get();
    }

    @PostMapping()
    public recipe create(recipe recipe){
        return recipeRepository.save(recipe);
    }

    @PutMapping("/{id}")
    public recipe update(@PathVariable Long id, recipe recipe){
        recipe recipeToUpdate = recipeRepository.findById(id).get();
        recipeToUpdate.setName(recipe.getName());
        recipeToUpdate.setQuantity(recipe.getQuantity());
        recipeToUpdate.setMainIngredient(recipe.getMainIngredient());
        return recipeRepository.save(recipeToUpdate);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id){
        recipeRepository.deleteById(id);
        return true;
    }
}
