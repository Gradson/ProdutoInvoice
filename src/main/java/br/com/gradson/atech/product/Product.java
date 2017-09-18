package br.com.gradson.atech.product;

import java.io.Serializable;

import javax.persistence.Entity;

import br.com.gradson.atech.entity.EntityDefault;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class Product extends EntityDefault implements Serializable {
	
	private static final long serialVersionUID = -7124091675519465069L;
	
	private String name;
	private String description;
}
