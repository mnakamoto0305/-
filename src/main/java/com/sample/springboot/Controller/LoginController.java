package com.sample.springboot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@GetMapping("/login")
	public ModelAndView getLogin(ModelAndView mav) {
		mav.setViewName("login");
		return mav;
	}

	@PostMapping("/login")
    public ModelAndView postLogin(ModelAndView mav) {

        //ホーム画面に遷移
        return new ModelAndView("redirect:/");
    }
}
