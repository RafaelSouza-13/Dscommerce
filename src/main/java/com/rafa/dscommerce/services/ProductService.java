package com.rafa.dscommerce.services;

import com.rafa.dscommerce.dto.ProductDTO;
import com.rafa.dscommerce.entities.Product;
import com.rafa.dscommerce.repositories.ProductRepository;
import com.rafa.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        //Optional<Product> result = repository.findById(1L);
        Product product = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Produto não encontrado"));
        return new ProductDTO(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable){
        Page<Product> result = repository.findAll(pageable);
        return result.map(x -> new ProductDTO(x));
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto){
        Product product = new Product();
        copyDtoToEntity(dto, product);
        product = repository.save(product);
        return new ProductDTO(product);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto){
        // Objeto monitorado pela JPA
        Product product = repository.getReferenceById(id);
        copyDtoToEntity(dto, product);
        product = repository.save(product);
        return new ProductDTO(product);
    }

    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }

    private void copyDtoToEntity(ProductDTO dto, Product product) {
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImgUrl(dto.getImgUrl());
    }


}
