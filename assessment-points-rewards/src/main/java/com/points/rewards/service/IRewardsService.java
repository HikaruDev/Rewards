package com.points.rewards.service;


import java.util.List;

import com.points.rewards.dto.ResponseTransactionDto;
import com.points.rewards.model.Rewards;

public interface IRewardsService {
	
	
	public List<Rewards> findAll();
	
	public Rewards findById(Long id);
	
	public void save(Rewards rewards);
	
	public Rewards updateTransaction(Rewards rewards);
	
	public void delete(Rewards rewards);
	
	List<ResponseTransactionDto> findHisTransactions();
	
	
}
