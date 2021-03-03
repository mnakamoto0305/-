package com.sample.springboot.domain;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class SimulationForm {

	@NotNull
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate referenceDate;
}
