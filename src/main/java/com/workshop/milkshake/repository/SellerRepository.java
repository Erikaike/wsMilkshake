package com.workshop.milkshake.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workshop.milkshake.entity.seller;

public interface SellerRepository extends JpaRepository<seller, Long>{
    
}
