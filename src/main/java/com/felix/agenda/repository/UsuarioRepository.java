package com.felix.agenda.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.felix.agenda.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Usuario findByUsername(String username);
	
}
