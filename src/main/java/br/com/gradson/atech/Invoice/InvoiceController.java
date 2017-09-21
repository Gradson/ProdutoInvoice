package br.com.gradson.atech.Invoice;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
	
	@Autowired
	private InvoiceService invoiceService;

	@PostMapping
	public Invoice create(@Valid @RequestBody Invoice invoice) throws Exception {
		return invoiceService.save(invoice);
	}
	
	@PostMapping(value = "/create-async")
	public boolean createAsync(@Valid @RequestBody List<Invoice> invoices) {
		return invoiceService.createAsync(invoices);
	}
	
	@GetMapping
	public Page<Invoice> getAll(Pageable pageable) {
		return invoiceService.findAll(pageable);
	}
	
	@GetMapping(value = "/search")
	public Page<Invoice> getAllByProductName(@RequestParam(required = false, defaultValue = "") String productName, 
											@RequestParam(required = false, defaultValue = "") String issuer,
												Pageable pageable) {
		return invoiceService.findBySearch(productName, issuer, pageable);
	}
}