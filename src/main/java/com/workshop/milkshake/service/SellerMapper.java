package com.workshop.milkshake.service;

import org.springframework.stereotype.Service;

import com.workshop.milkshake.dto.SellerDto;
import com.workshop.milkshake.entity.Seller;

@Service
public class SellerMapper {
    public SellerDto TransformSellerEntityInSellerDto(Seller seller) {
        SellerDto sellerDto = new SellerDto();
        sellerDto.setId(seller.getId());
        sellerDto.setName(seller.getName());
        sellerDto.setAge(seller.getAge());

        return sellerDto;
    }

    public Seller TransformSellerDtoInSellerEntity(SellerDto sellerDto) {
        Seller seller = new Seller();
        seller.setId(sellerDto.getId());
        seller.setName(sellerDto.getName());
        seller.setAge(sellerDto.getAge());

        return seller;
    }
}
