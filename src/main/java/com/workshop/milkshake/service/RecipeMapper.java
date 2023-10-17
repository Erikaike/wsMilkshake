package com.workshop.milkshake.service;

import org.springframework.stereotype.Service;

import com.workshop.milkshake.dto.RecipeDto;
import com.workshop.milkshake.entity.Recipe;

@Service
public class RecipeMapper {
    public RecipeDto TransformRecipeEntityInRecipeDto(Recipe recipe) {
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setId(recipe.getId());
        recipeDto.setName(recipe.getName());
        recipeDto.setMainIngredient(recipe.getMainIngredient());
        recipeDto.setQuantity(recipe.getQuantity());

        return recipeDto;
    }

    public Recipe TransformRecipeDtoInRecipeEntity(RecipeDto recipeDto) {
        Recipe recipe = new Recipe();
        recipe.setId(recipeDto.getId());
        recipe.setName(recipeDto.getName());
        recipe.setMainIngredient(recipeDto.getMainIngredient());
        recipe.setQuantity(recipeDto.getQuantity());

        return recipe;
    }
}
