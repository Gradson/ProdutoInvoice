package br.com.gradson.atech.Invoice;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class InvoiceService {
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	public Invoice save(Invoice invoice) throws Exception {
		invoice.calculate();
		Invoice invoiceSaved = invoiceRepository.save(invoice);
		
		return invoiceSaved.response();
	}

	public Page<Invoice> findAll(Pageable pageable) {
		return invoiceRepository.findAll(pageable);
	}

	public Page<Invoice> getAllByProductName(String productName, Pageable pageable) {
		return invoiceRepository.getAllByProductName(productName, pageable);
	}
}
