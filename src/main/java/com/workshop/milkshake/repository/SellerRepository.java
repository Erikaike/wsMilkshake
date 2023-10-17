package com.workshop.milkshake.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workshop.milkshake.entity.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long>{
    
}
