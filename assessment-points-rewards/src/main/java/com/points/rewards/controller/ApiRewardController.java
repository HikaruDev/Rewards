package com.points.rewards.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.points.rewards.dto.ResponseTransactionDto;
import com.points.rewards.model.Rewards;
import com.points.rewards.service.IRewardsService;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api")
public class ApiRewardController {
	
	Logger log = LoggerFactory.getLogger(ApiRewardController.class);
	
	@Autowired
	private IRewardsService rewardsService;
	
	@GetMapping("/AllTransactions")
	public List<Rewards> transactions(){
		return rewardsService.findAll();
	}
	
	@GetMapping("/TransactionId/{id}")
	public Rewards show(@PathVariable Long id) {
		return rewardsService.findById(id);
	}
	
	@GetMapping("/ThreeMonthsTransactions")
	public List<ResponseTransactionDto>  threeMonthsTransaction() {
		return rewardsService.findHisTransactions();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/savePurchase")
	public Rewards create(@RequestBody Rewards rewards) {
		rewardsService.save(rewards);
		return rewards;
	}
	
	@PutMapping("/updateTransaction")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Rewards update(@RequestBody Rewards rewards) {
		return rewardsService.updateTransaction(rewards);
	}
	
	@DeleteMapping("/deleteTransaction/{idClient}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long idClient) {
		Rewards currentTransaction = rewardsService.findById(idClient);
		rewardsService.delete(currentTransaction);
	}

}
