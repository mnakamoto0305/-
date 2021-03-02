package com.sample.springboot.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sample.springboot.Service.CalculationService;
import com.sample.springboot.domain.DateFormula;
import com.sample.springboot.domain.Result;
import com.sample.springboot.domain.SimulationForm;

@Controller
public class CalculationController {

	@Autowired
	CalculationService calculationService;

	@Autowired
	SimulationForm simulationForm;

	@Autowired
	Result result;

	@GetMapping("/")
	public ModelAndView getIndex(ModelAndView mav) {
		mav.setViewName("index");
		return mav;
	}

	@GetMapping("/result")
	public ModelAndView getResult(ModelAndView mav) {
		List<DateFormula> list = calculationService.findFormula();
		mav.addObject("dateFormulas", list);

		mav.setViewName("index");
		return mav;
	}

	@PostMapping("/")
	public ModelAndView postResult(@RequestParam("referenceDate")String str , ModelAndView mav) {
		//基準日付の取得
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate ldate = simulationForm.getReferenceDate();
		ldate = LocalDate.parse(str, fmt);

		//計算式の取得
		result.setDateFormula(calculationService.findFormula());
		//仮り置き
		result.setCalculationResult(ldate);

		mav.addObject("result", result);

		mav.addObject("referenceDate", ldate);

		mav.setViewName("index");

		return mav;
	}
}
