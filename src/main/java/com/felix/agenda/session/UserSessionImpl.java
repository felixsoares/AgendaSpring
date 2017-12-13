package com.felix.agenda.session;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.felix.agenda.model.Usuario;
import com.felix.agenda.service.UsuarioService;

public class UserSessionImpl implements UserSession{

	private Usuario user;

	@Override
	public Usuario getCurrentUser(UsuarioService usuarioService) {
		if (user == null) {
			if(usuarioService != null) {
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				user = usuarioService.findUserByUsername(auth.getName());
			}
		}
		
		return user;
	}
}