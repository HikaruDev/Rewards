package com.points.rewards.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.points.rewards.dto.ResponseTransactionDto;
import com.points.rewards.model.Rewards;
import com.points.rewards.repository.IRewardsRepository;
import com.points.rewards.utils.RewardsUtils;

@Service
public class RewardsServiceImpl implements IRewardsService{
	
	Logger log = LoggerFactory.getLogger(RewardsServiceImpl.class);
	
	@Autowired
	IRewardsRepository repository;
	
	@Autowired
	RewardsUtils rewardsUtils;

	@Override
	@Transactional
	public void save(Rewards rewards) {
		if(Objects.isNull(rewards.getDateTransaction())) {
			rewards.setDateTransaction(new Date());
		}else {
			rewards.setDateTransaction(rewards.getDateTransaction());
		}
		
		rewards.setPoints(rewardsUtils.calcRewards(rewards.getPurchase()));
		repository.save(rewards);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Rewards> findAll() {
		return (List<Rewards>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Rewards findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Rewards updateTransaction(Rewards rewards) {
		Rewards currentTransaction = findById(rewards.getId());
		currentTransaction.setPurchase(rewards.getPurchase());
		currentTransaction.setPoints(rewards.getPurchase());
		currentTransaction.setDateTransaction(rewards.getDateTransaction());
		
		save(currentTransaction);
		
		return currentTransaction;
	}

	@Override
	@Transactional
	public void delete(Rewards rewards) {
		repository.delete(rewards);
	}

	@Override
	@Transactional
	public List<ResponseTransactionDto> findHisTransactions() {
		List<ResponseTransactionDto> result = new ArrayList<ResponseTransactionDto>();
		List<Object[]> records = repository.findCustomerTransactions();
		
		for(Object[] data : records) {
			ResponseTransactionDto info = new ResponseTransactionDto();
			int totalPoint=0;
			
			log.info("customer ==> "+data[0]+" month "+data[1]+" points "+data[2]);
			info.setCustomer(String.valueOf(data[0]));
			info.setMonth(String.valueOf(data[1]));
			info.setRewardsPoints(Integer.valueOf(String.valueOf(data[2])));
			totalPoint += Integer.valueOf(String.valueOf(data[2]));
			info.setTotal(totalPoint);
			
			result.add(info);
		}
				
		return result;
	}

}
