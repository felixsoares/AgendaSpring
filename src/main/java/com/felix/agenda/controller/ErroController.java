package com.felix.agenda.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErroController {

	@ExceptionHandler(Throwable.class)
	public ModelAndView algumErro(HttpServletRequest httpRequest) {
		return new ModelAndView("error/error");
	}

}
