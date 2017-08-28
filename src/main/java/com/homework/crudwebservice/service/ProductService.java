package com.homework.crudwebservice.service;

import com.homework.crudwebservice.model.Product;
import com.homework.crudwebservice.repository.ProductJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService
{
    @Autowired
    private ProductJpaRepository productJpaRepository;

    public List<Product> findAllProducts()
    {
        return productJpaRepository.findAll();
    }
    public Product findById(long id)
    {
        return productJpaRepository.findById(id);
    }
    public Product findByName(String name)
    {
        return productJpaRepository.findByName(name);
    }
    public Product save(Product product)
    {
        return productJpaRepository.save(product);
    }
    public boolean isProductExist(Product product)
    {
        if(productJpaRepository.findByName(product.getName()) != null)
        {
            return true;
        }
        return false;
    }
    public void delete(Product product)
    {
        productJpaRepository.delete(product);
    }
}
