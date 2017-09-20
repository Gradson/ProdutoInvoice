package br.com.gradson.atech.Invoice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

	@Query("select inv from Invoice inv where inv.id in "
            + "(select inv.id from Invoice inv join inv.items it "
            + "join it.product p where p.name = :productName)")
	Page<Invoice> getAllByProductName(@Param("productName") String productName, Pageable pageable);

}
