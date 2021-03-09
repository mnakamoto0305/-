package com.sample.springboot.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sample.springboot.Service.CrudService;
import com.sample.springboot.domain.DateFormula;

@Controller
public class RegisterController {

	@Autowired
	CrudService crudService;

	@Autowired
	HttpSession session;

	@GetMapping("/register")
	public ModelAndView getRegisterForm(@ModelAttribute DateFormula dateFormula, ModelAndView mav) {
		mav.setViewName("register");
		return mav;
	}


	@GetMapping("/register/fix")
	public ModelAndView postBack(ModelAndView mav) {
		DateFormula dateFormula = (DateFormula) session.getAttribute("regFormula");
		mav.addObject("dateFormula", dateFormula);
		mav.setViewName("register");
		return mav;
	}


	@PostMapping("/register/confirm")
	public ModelAndView postRegisterConfirm(@ModelAttribute @Validated DateFormula dateFormula, BindingResult bindingResult, ModelAndView mav) {

		if(!bindingResult.hasErrors()) {
			session.setAttribute("regFormula", dateFormula);
			mav.setViewName("registerConfirm");
		} else {
			mav.setViewName("register");
		}

		return mav;
	}


	@PostMapping("/register")
	public ModelAndView postRegister(ModelAndView mav) {
		DateFormula dateFormula = (DateFormula) session.getAttribute("regFormula");
		crudService.registerFormula(dateFormula);
		session.removeAttribute("regFormula");
		return new ModelAndView("redirect:/");
	}

}
