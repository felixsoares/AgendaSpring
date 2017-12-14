package com.felix.agenda.service;

import java.util.List;

import com.felix.agenda.model.Tarefa;

public interface TarefaService {
	public List<Tarefa> findContatoByUser(Long id);
}
