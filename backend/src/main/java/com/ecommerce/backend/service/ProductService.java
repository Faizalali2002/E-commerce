package com.ecommerce.backend.service;


import com.ecommerce.backend.model.Category;
import com.ecommerce.backend.model.Product;
import com.ecommerce.backend.repository.CategoryRepo;
import com.ecommerce.backend.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    public Product addProduct(Product product, Long categoryId ){
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found : "+categoryId));
        product.setCategory(category);
        return productRepo.save(product);

    }

    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

    public Product getProductById(Long id){
        return productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found : "+id));
    }

    public Product updateProduct(Product updatedProduct, Long id){
        Product existingProduct  =  productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found : "+id));

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setImageUrl(updatedProduct.getImageUrl());
        existingProduct.setInStock(updatedProduct.isInStock());

        return productRepo.save(existingProduct);


    }

    public void deleteProduct(Long id){
        productRepo.deleteById(id);
    }

}
