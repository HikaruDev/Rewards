package com.points.rewards.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;



@Data
@Entity
@Table(name="rewards")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Rewards implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_transaction")
	private Long id;
	
	private String customer;
	private int purchase;
	private int points;
	
	@Column(name="date_transaction")
	@Temporal(TemporalType.DATE)
	private Date dateTransaction;
	
	private static final long serialVersionUID = -4589519673999626705L;

}