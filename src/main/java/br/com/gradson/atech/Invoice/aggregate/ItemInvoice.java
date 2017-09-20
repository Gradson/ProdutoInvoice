package br.com.gradson.atech.Invoice.aggregate;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import br.com.gradson.atech.Invoice.Invoice;
import br.com.gradson.atech.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class ItemInvoice implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	
	@OneToOne
	@NotNull
	@JoinColumn(name="PRODUCT_ID", referencedColumnName = "ID")
	private Product product;
	
	@NotNull
	private Integer quantity;
	
	@NotNull
	private BigDecimal price;
	
	@ManyToOne
	@JoinColumn(name = "INVOICE_ID", referencedColumnName = "ID")
	private Invoice invoice;

}
