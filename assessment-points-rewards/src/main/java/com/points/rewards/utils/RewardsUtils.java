package com.points.rewards.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RewardsUtils {

	Logger log = LoggerFactory.getLogger(RewardsUtils.class);

	private static final int HUNDRED = 100;
	private static final int FIFTY = 50;
	private static final int TWO = 2;

	public int calcRewards(int purchase) {
		int answer = 0;

		if (purchase >= FIFTY && purchase < HUNDRED) {
			answer = purchase - FIFTY;
		} else if (purchase > HUNDRED) {
			answer = (TWO * (purchase - HUNDRED) + FIFTY);
		}

		return answer;
	}
}
