package com.workshop.milkshake.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workshop.milkshake.dto.RecipeDto;
import com.workshop.milkshake.entity.Recipe;
import com.workshop.milkshake.repository.RecipeRepository;
import com.workshop.milkshake.service.RecipeMapper;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepo;
    @Autowired
    private RecipeMapper recipeMapper;


    @GetMapping()
    public List<Recipe> index(){
        return recipeRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Optional<Recipe> optionalRecipe = recipeRepo.findById(id);
        
        if(optionalRecipe.isPresent()) {
            Recipe recipe = optionalRecipe.get();
            RecipeDto recipeDto = recipeMapper.TransformRecipeEntityInRecipeDto(recipe);
            return ResponseEntity.ok(recipeDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pas trouvé 😝");
        }
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody RecipeDto recipeDto){
        if (recipeDto != null) {

            Recipe recipe = recipeMapper.TransformRecipeDtoInRecipeEntity(recipeDto);
            recipe = recipeRepo.save(recipe);

            return ResponseEntity.ok(recipe);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pas bon 😝");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody RecipeDto recipeDto) {
        Optional<Recipe> optionalRecipe = recipeRepo.findById(id);

        if (optionalRecipe.isPresent()) {
            Recipe recipe = optionalRecipe.get();
            
            recipe.setName(recipeDto.getName());
            recipe.setQuantity(recipeDto.getQuantity());
            recipe.setMainIngredient(recipeDto.getMainIngredient());

            recipe = recipeRepo.save(recipe);

            RecipeDto updatedDto = recipeMapper.TransformRecipeEntityInRecipeDto(recipe);

            return ResponseEntity.ok(updatedDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pas trouvé 😝");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, Recipe recipe){
        if(id != null) {
            recipeRepo.deleteById(id);
            return ResponseEntity.ok(recipe);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pas trouvé 😝");
        }
    }
}
