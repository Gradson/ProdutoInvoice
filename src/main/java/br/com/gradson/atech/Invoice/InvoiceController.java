package br.com.gradson.atech.Invoice;

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
	private InvoiceRepository invoiceRepository;

	@PostMapping()
	public Invoice create(@Valid @RequestBody Invoice invoice) throws Exception {
		invoice.calculate();
		Invoice invoiceSaved = invoiceRepository.save(invoice);
		
		return invoiceSaved.response();
	}
	
	@GetMapping
	public Page<Invoice> getAll(Pageable pageable) {
		return invoiceRepository.findAll(pageable);
	}
	
	@GetMapping(value = "/search")
	public Page<Invoice> getAllByProductName(@RequestParam(required = true) String productName, Pageable pageable) {
		return invoiceRepository.getAllByProductName(productName, pageable);
	}
}