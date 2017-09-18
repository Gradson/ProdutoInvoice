package br.com.gradson.atech.Invoice;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;

import br.com.gradson.atech.Invoice.aggregate.ItemInvoice;
import br.com.gradson.atech.entity.EntityDefault;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class Invoice extends EntityDefault implements Serializable {
	private static final long serialVersionUID = 577393299947044523L;
	
	private String observation;
	
	private Set<ItemInvoice> itens;

}
