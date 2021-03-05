package com.sample.springboot.domain;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class DateFormula {

	private Long id;

	@NotBlank
	private String dateId;

	@NotBlank
	private String name;

	@NotNull
	private Integer yearModeration;

	@NotNull
	private Integer monthModeration;

	@NotNull
	private Integer dayModeration;
	private LocalDate calculationResult;

}
