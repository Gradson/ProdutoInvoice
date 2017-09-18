package br.com.gradson.atech.entity;

import java.util.Date;

import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

public class EntityDefault {

	@Id
	private Long id;
	
	@CreationTimestamp
	private Date createDate;
	
	@UpdateTimestamp
	private Date updateDate;
}
