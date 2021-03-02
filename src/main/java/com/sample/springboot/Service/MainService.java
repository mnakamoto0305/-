package com.sample.springboot.Service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
public class MainService {

	public LocalDate moderationYear(LocalDate ldate, Integer yearModeration) {

		LocalDate ld = ldate.plusYears(yearModeration);
		return ld;

	}

}
