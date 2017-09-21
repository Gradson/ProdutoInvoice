package br.com.gradson.atech.Invoice;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import br.com.gradson.atech.Invoice.aggregate.ItemInvoice;
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
public class Invoice implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updateDate;
	
	@NotNull
	@NotBlank
	private String number;

	private String observation;
	
	private BigDecimal total;
	
	@OneToMany(mappedBy="invoice", cascade= {CascadeType.PERSIST, CascadeType.MERGE})
	private List<ItemInvoice> items;
	

	public void calculate() throws Exception {
		List<ItemInvoice> items = this.getItems();
		if(CollectionUtils.isEmpty(items)) {
			throw new Exception("not found items");
		}
		
		items.forEach(itemInvoice -> {
			itemInvoice.setInvoice(this);
			
			BigDecimal itemValue = itemInvoice.getPrice().multiply(new BigDecimal(itemInvoice.getQuantity()));
			this.total = getTotal().add(itemValue);
		});
	}
	
	public BigDecimal getTotal() {
		if(this.total == null) {
			this.total = new BigDecimal(0);
		}
		
		return this.total;
	}

	public Invoice response() {
		Invoice response = new Invoice();
		BeanUtils.copyProperties(this, response, "items");
		
		return response;
	}

}
