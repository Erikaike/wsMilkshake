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

import com.workshop.milkshake.dto.SellerDto;
import com.workshop.milkshake.entity.Seller;
import com.workshop.milkshake.repository.SellerRepository;
import com.workshop.milkshake.service.SellerMapper;

@RestController
@RequestMapping("/sellers")
public class SellerController {

    @Autowired
    private SellerRepository sellerRepo;
    @Autowired
    private SellerMapper sellerMapper;

    @GetMapping("/")
    public List<Seller> index(){
        return sellerRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        //Creation d'un optional
        Optional<Seller> optionalSeller = sellerRepo.findById(id);

        if(optionalSeller.isPresent()){
            Seller seller = optionalSeller.get();
            //Si trouvé, on le prend et convertion en DTO
            //données présentées à l'user entity=>DTO
            SellerDto sellerDto = sellerMapper.TransformSellerEntityInSellerDto(seller);
            
            return ResponseEntity.ok(sellerDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pas trouvé 😝");
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody SellerDto sellerDto){
        if(sellerDto != null) {
            //Le current user crée un DTO qu'on va convertir en entity avant de le persister
            //données à entrer en BDD DTO => entity
            Seller seller = sellerMapper.TransformSellerDtoInSellerEntity(sellerDto);
            seller = sellerRepo.save(seller);

            return ResponseEntity.ok(seller);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("nope");
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody SellerDto sellerDto){
        //Creation d'un optional
        Optional<Seller> optionalSeller = sellerRepo.findById(id);

        if(optionalSeller.isPresent()) {
            Seller seller = optionalSeller.get();

            //On fait ce qu'on a à faire
            seller.setName(sellerDto.getName());
            seller.setAge(sellerDto.getAge());

            seller = sellerRepo.save(seller);

            //Convertion en DTO pcq d
            SellerDto updatedDto = sellerMapper.TransformSellerEntityInSellerDto(seller);

            return ResponseEntity.ok(updatedDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pas trouvé 😝");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, Seller seller){
        if(id != null) {
            sellerRepo.deleteById(id);
            return ResponseEntity.ok(seller);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pas trouvé 😝");
        }
    }
}
