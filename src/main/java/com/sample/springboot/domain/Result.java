package com.sample.springboot.domain;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Result {

	private List<DateFormula> dateFormula;

}
