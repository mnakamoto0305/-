package com.sample.springboot.domain;

import java.time.LocalDate;

import lombok.Data;

@Data
public class DateFormula {

	private int id;
	private String dateId;
	private String name;
	private int yearModeration;
	private int monthModeration;
	private int dayModeration;
	private LocalDate calculationResult;

}
