package com.sample.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@GetMapping("/login")
	public ModelAndView getLogin(ModelAndView mav) {
		mav.setViewName("login");
		return mav;
	}


}
