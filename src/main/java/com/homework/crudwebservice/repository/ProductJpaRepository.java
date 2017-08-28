package com.homework.crudwebservice.repository;

import com.homework.crudwebservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ProductJpaRepository extends JpaRepository<Product, Long>
{
    Product findById(Long id);
    Product findByName(String name);
}
