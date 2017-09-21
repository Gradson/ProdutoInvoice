package br.com.gradson.atech.Invoice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.gradson.atech.Invoice.aggregate.ItemInvoice;
import br.com.gradson.atech.product.Product;
import br.com.gradson.atech.product.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@Rollback
public class InvoiceServiceIntegrationTest {
	
	@Autowired
	private InvoiceService service;
	@Autowired
	private ProductService productService;

	@Before
	public void setUp() throws Exception {
	}

	@Test(expected = Exception.class)
	public void shouldNotSaveInvoiceWhithoutItems() throws Exception {
		service.save(new Invoice());
	}
	
	@Test
	public void shouldSaveInvoiceAndReturnWithoutItems() throws Exception {
		Invoice invoice = Invoice.builder().number("045").items(buildItems("celular")).build();
		Invoice invoiceSaved = service.save(invoice);
		
		assertNotNull(invoiceSaved.getId());
		assertNull(invoiceSaved.getItems());
	}
	
	@Test
	public void shouldFindInvoicesByProductName() throws Exception {
		Invoice invoice1 = Invoice.builder().number("001").items(buildItems("celular")).build();
		Invoice invoice2 = Invoice.builder().number("002").items(buildItems("caixa", "tesoura")).build();
		Invoice invoice3 = Invoice.builder().number("003").items(buildItems("mesa")).build();
		
		saveInvoices(invoice1, invoice2, invoice3);
		
		Page<Invoice> invoicesPage = service.getAllByProductName("c", null);
		
		List<Invoice> invoices = invoicesPage.getContent();
		assertEquals(2, invoices.size());
		assertEquals("001", invoices.get(0).getNumber());
		assertEquals("002", invoices.get(1).getNumber());
	}
	
	private void saveInvoices(Invoice ...invoices) throws Exception {
		for (Invoice invoice : invoices) {
			service.save(invoice);
		}
	}

	private List<ItemInvoice> buildItems(final String ...productNames) {
		List<ItemInvoice> items = new ArrayList<>();
		
		int quantity = 1;
		for (String productName : productNames) {
			ItemInvoice itemInvoice = ItemInvoice.builder().product(createProduct(productName))
					.price(new BigDecimal(5)).quantity(quantity).build();
			
			items.add(itemInvoice);
			
			quantity++;
		}
		
		return items;
	}

	private Product createProduct(final String productName) {
		Product product = Product.builder().name(productName).build();
		return productService.save(product);
	}

}
