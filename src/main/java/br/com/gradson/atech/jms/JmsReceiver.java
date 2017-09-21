package br.com.gradson.atech.jms;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import br.com.gradson.atech.Invoice.Invoice;
import br.com.gradson.atech.Invoice.InvoiceService;
import br.com.gradson.atech.product.Product;
import br.com.gradson.atech.product.ProductService;

@Component
public class JmsReceiver {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private InvoiceService invoiceService;
	
	@JmsListener(destination="QUEUE_PRODUCT")
	public void processProduct(final Product product) throws IOException {
		productService.save(product);
	}
	
	@JmsListener(destination="QUEUE_INVOICE")
	public void processInvoice(final Invoice invoice) throws Exception {
		invoiceService.save(invoice);
	}
}
