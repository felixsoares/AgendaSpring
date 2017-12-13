package com.felix.agenda.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.felix.agenda.model.Usuario;
import com.felix.agenda.repository.UsuarioRepository;
import com.felix.agenda.service.UsuarioService;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Usuario findUserByUsername(String username) {
		return usuarioRepository.findByUsername(username);
	}

	@Override
	public void salvar(Usuario usuario) {
		usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
		usuario.setAtivo(true);
		usuarioRepository.save(usuario);
	}

}
