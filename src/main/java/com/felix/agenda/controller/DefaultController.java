package com.felix.agenda.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.felix.agenda.service.UsuarioService;
import com.felix.agenda.session.UserSession;
import com.felix.agenda.session.UserSessionImpl;

public class DefaultController {

	protected UserSession userSession = new UserSessionImpl();

	@Autowired
	public UsuarioService usuarioService;

}
