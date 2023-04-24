package com.teamgalactic.aztcg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamgalactic.aztcg.entity.CardType;
import com.teamgalactic.aztcg.entity.Product;
import com.teamgalactic.aztcg.entity.User;
import com.teamgalactic.aztcg.repository.CardTypeRepository;
import com.teamgalactic.aztcg.repository.ProductRepository;
import com.teamgalactic.aztcg.request.CreateProductRequest;
import com.teamgalactic.aztcg.request.UpdateProductRequest;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CardTypeRepository cardTypeRepository;
	
	public Product getProduct(Long id) {
	
		return productRepository.findById(id).get();
	}
	
	public Product createProduct(CreateProductRequest createProductRequest) {
		Product product = new Product(createProductRequest);
		
		CardType cardType = cardTypeRepository.findById(createProductRequest.getCardTypeId()).get();
		
		product.setCardType(cardType);
		
		product = productRepository.save(product);
		
		return product;
	}
	
	
	public Product updateProduct(UpdateProductRequest updateProductRequest) {
		Product product = productRepository.findById(updateProductRequest.getId()).get();
		
		String name = updateProductRequest.getName();
		String description = updateProductRequest.getDescription();
		String cardKey  = updateProductRequest.getCardKey();
		Integer quantityOnHand = updateProductRequest.getQuantityOnHand();
		Double price = updateProductRequest.getPrice();
		String rarity = updateProductRequest.getRarity();
		
		if (updateProductRequest.getCardTypeId() != null) {
			CardType cardType = cardTypeRepository.findById(updateProductRequest.getCardTypeId()).get();
			product.setCardType(cardType);
		}
		
		if (name != null && !name.isEmpty()) product.setName(name);
		if (description != null && !description.isEmpty()) product.setDescription(description);
		if (cardKey != null && !cardKey.isEmpty()) product.setCardKey(cardKey);
		if (quantityOnHand != null) product.setQuantityOnHand(quantityOnHand);
		if (price != null) product.setPrice(price);
		if (rarity != null && !rarity.isEmpty()) product.setRarity(rarity);
		
		product = productRepository.save(product);
		
		return product;
	}
	
	public String deleteProduct(Long id) {
		productRepository.deleteById(id);
		return "Product with id " + id + " has been deleted successfully";
	}
	

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

}
