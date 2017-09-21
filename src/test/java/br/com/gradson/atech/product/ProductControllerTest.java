package br.com.gradson.atech.product;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.gradson.atech.BaseControllerTest;

public class ProductControllerTest extends BaseControllerTest {
	
	private static final String ENDPOINT = "/api/products";
	
	@InjectMocks
	private ProductController productController;
	
	@Mock
	private ProductService productService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		super.setUp(productController);
	}

	@Test
	public void shouldCreateProduct() throws Exception {
		Product product = Product.builder().name("celular").build();
		mockMvc.perform(post(ENDPOINT).content(toJson(product))).andExpect(status().isOk());
	}

	@Test
	public void shouldNotCreateProductWithoutName() throws Exception {
		Product product = Product.builder().build();
		mockMvc.perform(post(ENDPOINT).content(toJson(product))).andExpect(status().isBadRequest());
	}
	
	@Test
	public void shouldGetAllProducts() throws Exception {
		mockMvc.perform(get(ENDPOINT)).andExpect(status().isOk());
	}

	@Test
	public void shouldCreateProductsAsync() throws Exception {
		Product product = Product.builder().name("celular").build();
		List<Product> products = Arrays.asList(product);
		
		mockMvc.perform(post(ENDPOINT + "/create-async").content(toJson(products))).andExpect(status().isOk());
	}
}
