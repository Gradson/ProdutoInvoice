package br.com.gradson.atech.Invoice.aggregate;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;

import br.com.gradson.atech.entity.EntityDefault;
import br.com.gradson.atech.product.Product;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class ItemInvoice extends EntityDefault implements Serializable {
	private static final long serialVersionUID = -443169153568284889L;
	
	private Product product;
	
	private Integer quantity;
	
	private BigDecimal price;

}
