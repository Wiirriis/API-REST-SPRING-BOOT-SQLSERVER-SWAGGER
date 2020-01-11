package com.init.products.dao;
import com.init.products.entitys.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsDAO extends JpaRepository<Product, Long>{

}
