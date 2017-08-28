package com.homework.crudwebservice.controller;

import com.homework.crudwebservice.service.ProductService;
import com.homework.crudwebservice.errors.CustomErrorType;
import com.homework.crudwebservice.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController
{

    public static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    // -------------------Retrieve All Products---------------------------------------------

    @GetMapping(value = "/all")
    public ResponseEntity<List<Product>> listAllProducts()
    {
        List<Product> products = productService.findAllProducts();
        if (products.isEmpty())
        {
            return new ResponseEntity(new CustomErrorType("Product List is Empty"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    // -------------------Retrieve Single Product------------------------------------------

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getProductById(@PathVariable final long id)
    {
        logger.info("Fetching Product with id {}", id);
        Product product = productService.findById(id);
        if (product == null)
        {
            logger.error("Product with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Product with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

        // -------------------Create a Product-------------------------------------------

        @PostMapping(value = "/create")
        public ResponseEntity<?> create(@RequestBody final Product product /*, UriComponentsBuilder ucBuilder */) {
            logger.info("Creating Product : {}", product);

            if(product.getName() == null || product.getName().isEmpty())
            {
                logger.error("Bad Request");
                return new ResponseEntity(new CustomErrorType("Unable to create. Invalid Input."),HttpStatus.BAD_REQUEST);
            }
            if (productService.isProductExist(product)) {
                logger.error("Unable to create. A Product with name {} already exist", product.getName());
                return new ResponseEntity(new CustomErrorType("Unable to create. A Product with name " +
                        product.getName() + " already exist."),HttpStatus.CONFLICT);
            }
            productService.save(product);

            return new ResponseEntity<Product>(product, HttpStatus.CREATED);
        }

        // ------------------- Update a Product ------------------------------------------------

        @PutMapping(value = "/{id}")
        public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Product product) {
            logger.info("Updating Product with id {}", id);

            Product currentProduct = productService.findById(id);

            if (currentProduct == null) {
                logger.error("Unable to update. Product with id {} not found.", id);
                return new ResponseEntity(new CustomErrorType("Unable to upate. Product with id " + id + " not found."),
                        HttpStatus.NOT_FOUND);
            }

            currentProduct.setName(product.getName());
            currentProduct.setDescription(product.getDescription());

            productService.save(currentProduct);
            return new ResponseEntity<Product>(currentProduct, HttpStatus.OK);
        }


        // ------------------- Delete a Product-----------------------------------------

        @DeleteMapping(value = "/{id}")
        public ResponseEntity<?> delete(@PathVariable("id") long id) {
            logger.info("Fetching & Deleting Product with id {}", id);

            Product product = productService.findById(id);
            if (product == null) {
                logger.error("Unable to delete. Product with id {} not found.", id);
                return new ResponseEntity(new CustomErrorType("Unable to delete. Product with id " + id + " not found."),
                        HttpStatus.NOT_FOUND);
            }
            productService.delete(product);
            return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
        }

}
