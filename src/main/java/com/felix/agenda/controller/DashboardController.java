package com.felix.agenda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/dashboard")
public class DashboardController extends DefaultController{

	@GetMapping
	public ModelAndView inicio() {
		ModelAndView modelAndView = new ModelAndView("dashboard/dashboard");
		modelAndView.addObject("nomePagina", "Dashbaord");
		modelAndView.addObject("usuario", userSession.getCurrentUser(usuarioService));
		return modelAndView;
	}

}
