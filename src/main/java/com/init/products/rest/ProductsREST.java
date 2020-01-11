package com.init.products.rest;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.init.products.dao.ProductsDAO;
import com.init.products.entitys.Product;;

@RestController
@RequestMapping("products")
public class ProductsREST {
	
	@Autowired
	private ProductsDAO productDAO;
	
	//GET ALL
	@GetMapping
	public ResponseEntity<List<Product>> getProduct() {
		List<Product> products = productDAO.findAll();
		return ResponseEntity.ok(products);
	}
	
	//GET BY ID
	@RequestMapping(value="{id}") //Escucharia en localhost:PORT/products/{id}
	public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
		Optional<Product> optionalProduct = productDAO.findById(id);
		if(optionalProduct.isPresent()) {
			return ResponseEntity.ok(optionalProduct.get());
		}else {
			return ResponseEntity.noContent().build();
			
		}
		
	}
	
	//POST
	@PostMapping //products (POST)
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
		Product newProduct =  productDAO.save(product);
		return ResponseEntity.ok(newProduct);
	}
	
	//DELETE
	@DeleteMapping(value="{id}") //products(DELETE)
	public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id){
		productDAO.deleteById(id);
		return ResponseEntity.ok(null);
	}

	//PUT
	@PutMapping
	public ResponseEntity<Product> updateProduct(@RequestBody Product product){
		Optional<Product> optionalProduct = productDAO.findById(product.getId());
		if(optionalProduct.isPresent()) {
			Product update = optionalProduct.get();
			update.setName(product.getName());
			update.setTelefono(product.getTelefono());
			update.setUbicacion(product.getUbicacion());
			productDAO.save(update);
			return ResponseEntity.ok(update);
		}else {
			return ResponseEntity.notFound().build();
			
		}
		
	}



}
