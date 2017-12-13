package com.felix.agenda.service;

import com.felix.agenda.model.Usuario;

public interface UsuarioService {
	public Usuario findUserByUsername(String username);

	public void salvar(Usuario user);
}
