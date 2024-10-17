package com.example.hosting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hosting.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
