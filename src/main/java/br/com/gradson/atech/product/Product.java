package br.com.gradson.atech.product;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
public class Product implements Serializable {
	
	private static final long serialVersionUID = -7124091675519465069L;
	
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
	@Size(max=30)
	private String name;
	
	@Size(max=100)
	private String description;
}
