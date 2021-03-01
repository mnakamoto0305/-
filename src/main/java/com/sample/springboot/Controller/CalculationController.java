package com.sample.springboot.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sample.springboot.Service.CalculationService;
import com.sample.springboot.domain.DateFormula;

@Controller
public class CalculationController {

	@Autowired
	CalculationService calculationService;

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

//	@PostMapping("/")
//	public ModelAndView postResult(ModelAndView mav) {
//		mav.setViewName("index");
//		return mav;
//	}
}
