package br.com.gradson.atech.Invoice.aggregate;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemInvoiceRepository extends JpaRepository<ItemInvoice, Long>{

}
