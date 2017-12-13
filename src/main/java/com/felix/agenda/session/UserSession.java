package com.felix.agenda.session;

import com.felix.agenda.model.Usuario;
import com.felix.agenda.service.UsuarioService;

public interface UserSession {
	public Usuario getCurrentUser(UsuarioService usuarioService);
}
