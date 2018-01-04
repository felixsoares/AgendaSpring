package com.felix.agenda.util;

import org.springframework.web.servlet.ModelAndView;

import com.felix.agenda.model.Usuario;
import com.felix.agenda.service.UsuarioService;
import com.felix.agenda.session.UserSession;

public class CustomModelAndView extends ModelAndView {
	
	private Usuario user;
	
	public CustomModelAndView(UserSession userSession, UsuarioService usuarioService, String view){
		super(view);
		setUser(userSession.getCurrentUser(usuarioService));
		addObject("usuario", user);
	}
	
	public CustomModelAndView(UserSession userSession, UsuarioService usuarioService){
		setUser(userSession.getCurrentUser(usuarioService));
		addObject("usuario", user);
	}
	
	public Usuario getUser() {
		return user;
	}
	
	
	public void setUser(Usuario user) {
		this.user = user;
	}
}
