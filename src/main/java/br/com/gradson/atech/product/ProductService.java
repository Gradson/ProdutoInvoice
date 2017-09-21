package br.com.gradson.atech.product;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.gradson.atech.jms.JmsQueueType;
import br.com.gradson.atech.jms.JmsSender;

@Service
@Transactional
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private JmsSender jmsSender;

	public Product save(Product product) {
		return productRepository.save(product);
	}

	public Page<Product> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	public boolean createAsync(List<Product> products) {
		jmsSender.toQueue(products, JmsQueueType.QUEUE_PRODUCT);
		return true;
	}

}
