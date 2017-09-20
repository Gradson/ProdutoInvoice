package br.com.gradson.atech.Invoice;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.gradson.atech.Invoice.aggregate.ItemInvoice;
import br.com.gradson.atech.Invoice.aggregate.ItemInvoiceRepository;

@Service
@Transactional
public class InvoiceService {
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	public Invoice save(final Invoice invoice) throws Exception {
		invoice.calculate();
		return  invoiceRepository.save(invoice).response();
	}

	public Page<Invoice> findAll(Pageable pageable) {
		return invoiceRepository.findAll(pageable);
	}

	public Page<Invoice> getAllByProductName(final String productName, Pageable pageable) {
		return invoiceRepository.getAllByProductName(productName, pageable);
	}
}
