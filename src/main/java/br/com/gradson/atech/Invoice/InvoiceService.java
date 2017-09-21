package br.com.gradson.atech.Invoice;

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
public class InvoiceService {
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private JmsSender jmsSender;
	
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

	public boolean createAsync(List<Invoice> invoices) {
		jmsSender.toQueue(invoices, JmsQueueType.QUEUE_INVOICE);
		return true;
	}
}
