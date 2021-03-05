package com.sample.springboot.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sample.springboot.Service.CrudService;
import com.sample.springboot.domain.DateFormula;

@Controller
public class UpdateController {

	@Autowired
	CrudService crudService;

	@Autowired
	HttpSession session;


	@GetMapping("update/{id}")
	public ModelAndView getUpdate(@PathVariable Long id ,ModelAndView mav) {
		DateFormula dateFormula = crudService.identifyFormula(id);
		mav.addObject("dateFormula", dateFormula);
		mav.setViewName("update");
		return mav;
	}

	@PostMapping("update/confirm")
	public ModelAndView postUpdateConfirm(@ModelAttribute @Validated DateFormula dateFormula, BindingResult bindingResult, ModelAndView mav) {
		if (!bindingResult.hasErrors()) {
			session.setAttribute("updFormula", dateFormula);
			mav.setViewName("updateConfirm");
		} else {
			mav.setViewName("update");
		}
		return mav;
	}

	@PutMapping("update")
	public ModelAndView putUpdate(ModelAndView mav) {
		DateFormula dateFormula = (DateFormula) session.getAttribute("updFormula");
		crudService.updateFormula(dateFormula);
		session.invalidate();
		return new ModelAndView("redirect:/");
	}
}
