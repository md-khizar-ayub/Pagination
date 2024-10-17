package com.mka.pagination.controller;


import com.mka.pagination.entity.Product;
import com.mka.pagination.exception.ProductNotFountException;
import com.mka.pagination.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<Product> createproduct(@RequestBody Product product){
        return ResponseEntity.ok(productRepository.save(product));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProductList(){
        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping("/{id}")

    public ResponseEntity<Product> getProduct(@PathVariable Integer id){
        return ResponseEntity.ok(productRepository.findById(id).orElseThrow(() -> new ProductNotFountException("product Not Found Exception")));
    }
    @ExceptionHandler(ProductNotFountException.class)
    public ResponseEntity<String> handleProductNotFountException(){
        return ResponseEntity.ok("Product Not Found");
    }
    @GetMapping("/pagination")
    public ResponseEntity<List<Product>> getProductPagination(@RequestParam int page,@RequestParam int size){
//        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        Pageable pageable = PageRequest.of(page, size);
        List<Product> products = productRepository.findAll(pageable).getContent();
        return ResponseEntity.ok(products);

    }
}
