package com.points.rewards.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseTransactionDto implements Serializable {
	
	private String customer;
	private String month;
	private int rewardsPoints;
	private int total;
	
	private static final long serialVersionUID = -6421471814759194151L;
	
}
