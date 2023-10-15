package com.workshop.milkshake.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workshop.milkshake.entity.seller;
import com.workshop.milkshake.repository.SellerRepository;

@RestController
public class SellerController {

    @Autowired
    SellerRepository sellerRepository;

    @GetMapping("/sellers")
    public List<seller> index(){
        return sellerRepository.findAll();
    }

    @GetMapping("/sellers/{id}")
    public seller show(@PathVariable Long id){
        return sellerRepository.findById(id).get();
    }

    @PostMapping("/sellers")
    public seller create(seller seller){
        return sellerRepository.save(seller);
    }
    
    @PutMapping("/sellers/{id}")
    public seller update(@PathVariable Long id, seller seller){
        seller sellerToUpdate = sellerRepository.findById(id).get();
        sellerToUpdate.setName(seller.getName());
        sellerToUpdate.setAge(seller.getAge());
        return sellerRepository.save(sellerToUpdate);
    }

    @DeleteMapping("/sellers/{id}")
    public boolean delete(@PathVariable Long id){
        sellerRepository.deleteById(id);
        return true;
    }
}
