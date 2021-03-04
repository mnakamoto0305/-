package com.sample.springboot.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sample.springboot.Service.CalculationService;
import com.sample.springboot.Service.CrudService;
import com.sample.springboot.domain.DateFormula;
import com.sample.springboot.domain.Result;
import com.sample.springboot.domain.SimulationForm;

@Controller
public class CalculationController {

	@Autowired
	CrudService crudService;

	@Autowired
	CalculationService calculationService;

	@Autowired
	SimulationForm simulationForm;

	@GetMapping("/")
	public ModelAndView getIndex(@ModelAttribute SimulationForm simulationForm ,ModelAndView mav) {
		mav.setViewName("index");
		return mav;
	}

	@GetMapping("/result")
	public ModelAndView getResult(ModelAndView mav) {
		List<DateFormula> list = crudService.findFormula();
		mav.addObject("dateFormulas", list);

		mav.setViewName("index");
		return mav;
	}

	@PostMapping("/")
	public ModelAndView postResult(@ModelAttribute Result result, @ModelAttribute @Validated SimulationForm simulationForm,BindingResult bindingResult ,@RequestParam("referenceDate")String str , ModelAndView mav) {

		if (!bindingResult.hasErrors()) {
			//計算式の取得
			result.setDateFormula(crudService.findFormula());

			//計算実行
			for (DateFormula df : result.getDateFormula()) {
				LocalDate resultDate = calculationService.moderation(simulationForm.getReferenceDate(), df.getYearModeration(), df.getMonthModeration(), df.getDayModeration());
				df.setCalculationResult(resultDate);
			}

			//addobject
			mav.addObject("result", result);

			mav.setViewName("index");
		} else {
			mav.setViewName("index");
		}

		return mav;
	}
}
