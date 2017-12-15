package com.felix.agenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.felix.agenda.service.UsuarioService;
import com.felix.agenda.session.UserSession;
import com.felix.agenda.session.UserSessionImpl;

public abstract class DefaultController {

	protected UserSession userSession = new UserSessionImpl();

	@Autowired
	public UsuarioService usuarioService;
	
	public abstract ModelAndView inicio();

}
