package com.sample.springboot.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.sample.springboot.Service.CrudService;
import com.sample.springboot.domain.DateFormula;

@Controller
public class DeleteController {

	@Autowired
	CrudService crudService;

	@Autowired
	HttpSession session;


	@GetMapping("delete/{id}")
	public ModelAndView getDeleteConfirm(@PathVariable Long id ,ModelAndView mav) {
		DateFormula dateFormula = crudService.identifyFormula(id);
		mav.addObject("dateFormula", dateFormula);
		mav.setViewName("deleteConfirm");
		return mav;
	}


	@DeleteMapping("delete/{id}")
	public ModelAndView delete(@PathVariable Long id, ModelAndView mav) {
		crudService.deleteFormula(id);
		return new ModelAndView("redirect:/");
	}


}
