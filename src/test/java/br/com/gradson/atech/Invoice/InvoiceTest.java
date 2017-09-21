package br.com.gradson.atech.Invoice;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Test;

import br.com.gradson.atech.Invoice.aggregate.ItemInvoice;

public class InvoiceTest {

	@Test
	public void shouldCalculateTotalItemsAndReturn77() throws Exception {
		ItemInvoice item1 = ItemInvoice.builder().price(new BigDecimal(9)).quantity(5).build();
		ItemInvoice item2 = ItemInvoice.builder().price(new BigDecimal(8)).quantity(4).build();
		
		Invoice invoice = new Invoice();
		invoice.setItems(Arrays.asList(item1, item2));
		
		invoice.calculate();
		
		assertTrue(new BigDecimal(77).equals(invoice.getTotal()));
	}
	
	@Test(expected = Exception.class)
	public void shouldReturnExceptionWhenInvoiceNoItems() throws Exception {
		Invoice invoice = new Invoice();
		invoice.calculate();
	}
}
