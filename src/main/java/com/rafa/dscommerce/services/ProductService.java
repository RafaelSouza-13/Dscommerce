package com.rafa.dscommerce.services;

import com.rafa.dscommerce.dto.ProductDTO;
import com.rafa.dscommerce.entities.Product;
import com.rafa.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        //Optional<Product> result = repository.findById(1L);
        Product product = repository.findById(id).get();
        return new ProductDTO(product);
    }
}
