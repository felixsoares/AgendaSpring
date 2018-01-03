package com.felix.agenda.service;

import java.util.List;

import com.felix.agenda.model.Tarefa;

public interface TarefaService {
	public List<Tarefa> findAll();
	public List<Tarefa> findByUser(Long id);
	public List<Tarefa> findByContato(Long id, Long idContato);
	public Tarefa findById(Long id);
	public void marcar(Long id);
	public void delete(Long id);
	public void save(Tarefa tarefa);
}
