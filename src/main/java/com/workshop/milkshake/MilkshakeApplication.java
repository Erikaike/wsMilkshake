package com.workshop.milkshake;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.workshop.milkshake.entity.recipe;
import com.workshop.milkshake.entity.seller;
import com.workshop.milkshake.repository.RecipeRepository;
import com.workshop.milkshake.repository.SellerRepository;

@SpringBootApplication
public class MilkshakeApplication {

	@Autowired
    private RecipeRepository rRepo;
	@Autowired
    private SellerRepository sRepo;

	public static void main(String[] args) {
		SpringApplication.run(MilkshakeApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() throws Exception {
		return (String[] args) -> {
			rRepo.save(new recipe("Strawberry Extasy", 15, "fraise"));
			rRepo.save(new recipe("Rocher Ferrero", 30, "Chocolat"));
			rRepo.save(new recipe("Antanarivo", 62, "Vanille"));
			rRepo.save(new recipe("Carmen Miranda", 20, "Banane"));
			sRepo.save(new seller("Olivia", 32));
			sRepo.save(new seller("Laura", 23));
		};
	}

}
