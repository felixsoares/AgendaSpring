package com.felix.agenda.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.felix.agenda.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

	@Query("select t from Tarefa t inner join t.usuario u where u.id = :idUser order by t.id desc")
	List<Tarefa> findByUsuario(@Param("idUser") Long id, Pageable pageable);
	
	@Query("select t from Tarefa t inner join t.usuario u join t.contatos ct where u.id = :idUser and ct.id = :idContato")
	List<Tarefa> findByContato(@Param("idUser") Long id, @Param("idContato") Long idContato);

}
