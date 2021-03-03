package com.sample.springboot.Service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
public class CalculationService {

	public LocalDate moderation(LocalDate ldate, Integer yearModeration, Integer monthModeration, Integer dayModeration) {

		LocalDate ld = ldate.plusYears(yearModeration);
		ld = ld.plusMonths(monthModeration);
		ld = ld.plusDays(dayModeration);
		return ld;

	}

}
