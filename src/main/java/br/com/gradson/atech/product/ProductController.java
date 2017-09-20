package br.com.gradson.atech.product;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductService productRepository;

	@PostMapping()
	public Product create(@Valid @RequestBody Product product) {
		return productRepository.save(product);
	}
	
	@GetMapping
	public Page<Product> getAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}
}