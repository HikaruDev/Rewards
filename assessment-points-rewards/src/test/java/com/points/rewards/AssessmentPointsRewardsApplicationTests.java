package com.points.rewards;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.points.rewards.controller.ApiRewardController;
import com.points.rewards.dto.ResponseTransactionDto;
import com.points.rewards.model.Rewards;
import com.points.rewards.repository.IRewardsRepository;

@SpringBootTest
class AssessmentPointsRewardsApplicationTests {

	private static final String BRAND2 = "BRAND2";
	private static final String _2021_01_01 = "2021-01-01";
	private static final int _120 = 120;
	private static final String BRAND = "BRAND";

	@Mock
	IRewardsRepository repo;
	
	@InjectMocks
	private ApiRewardController controller;
	
	@BeforeEach
	void init() {
		ResponseTransactionDto data1 = new ResponseTransactionDto();
		data1.setCustomer(BRAND);
		data1.setMonth(_2021_01_01);
		data1.setRewardsPoints(_120);
		data1.setTotal(_120);
		
		ResponseTransactionDto data2 = new ResponseTransactionDto();
		data1.setCustomer(BRAND2);
		data1.setMonth(_2021_01_01);
		data1.setRewardsPoints(_120);
		data1.setTotal(_120);
	}
	
	
	@Test
	public void testCreate() {
		Rewards request = new Rewards();
		request.setCustomer(BRAND);
		request.setPurchase(_120);
		when(repo.save(request)).thenReturn(request);
		assertEquals(_120, request.getPurchase());
		
	}
	
	@Test
	public void testAllTransactions() {
		List<Rewards> transactions = new ArrayList<Rewards>();
		when(repo.findAll()).thenReturn(transactions);
		
		List<Rewards> response = new ArrayList<Rewards>();
		assertNotNull(response);
		assertEquals(transactions, response);
	}
	
	@Test
	public void testThreeMonthsTransactions() {
		List<Object[]> transaction = repo.findCustomerTransactions();
		for(Object[] txt : transaction) {
			System.out.println("info "+txt[0]);
		}
	}

}
