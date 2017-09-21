package br.com.gradson.atech.Invoice;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.gradson.atech.BaseControllerTest;

public class InvoiceControllerTest extends BaseControllerTest {
	
	private static final String ENDPOINT = "/api/invoices";
	
	@InjectMocks
	private InvoiceController invoiceController;
	
	@Mock
	private InvoiceService invoiceService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		super.setUp(invoiceController);
	}
	
	@Test
	public void shouldCreateInvoice() throws Exception {
		Invoice invoice = Invoice.builder().number("3344").build();
		mockMvc.perform(post(ENDPOINT).content(toJson(invoice))).andExpect(status().isOk());
	}

	@Test
	public void shouldNotCreateInvoiceWithoutNumber() throws Exception {
		Invoice invoice = Invoice.builder().build();
		mockMvc.perform(post(ENDPOINT).content(toJson(invoice))).andExpect(status().isBadRequest());
	}
	
	@Test
	public void shouldGetAllInvoices() throws Exception {
		mockMvc.perform(get(ENDPOINT)).andExpect(status().isOk());
	}
	
	@Test
	public void shouldGetAllInvoicesByProductName() throws Exception {
		mockMvc.perform(get(ENDPOINT + "/search").param("productName", "celular")).andExpect(status().isOk());
	}
	
	@Test
	public void shoulNotGetInvoicesWithoutProductName() throws Exception {
		mockMvc.perform(get(ENDPOINT + "/search")).andExpect(status().isBadRequest());
	}
	
	@Test
	public void shouldCreateInvoicesAsync() throws Exception {
		Invoice invoice = Invoice.builder().number("3344").build();
		List<Invoice> invoices = Arrays.asList(invoice);
		
		mockMvc.perform(post(ENDPOINT + "/create-async").content(toJson(invoices))).andExpect(status().isOk());
	}
}
