package com.felix.agenda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.felix.agenda.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

	@Query("select c from Contato c join c.usuario u where u.id = :idUser")
	List<Contato> findByUsuario(@Param("idUser") Long id);

}
