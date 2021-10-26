package com.points.rewards.repository;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.points.rewards.model.Rewards;

@Repository
public interface IRewardsRepository extends JpaRepository<Rewards, Serializable> {

	@Query(value = "select  customer, to_char(date_transaction,'Mon') Month, sum(points) rewards_points \n"+
				   "from REWARDS \n" +
				   "where date_transaction <= DATE_TRUNC('MONTH', LOCALTIMESTAMP - INTERVAL '3' MONTH) \n"+
				   "group by customer, to_char(date_transaction,'Mon')", 
				   nativeQuery = true)
	List<Object[]> findCustomerTransactions();

}