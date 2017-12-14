package com.felix.agenda.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felix.agenda.model.Tarefa;
import com.felix.agenda.repository.TarefaRepository;
import com.felix.agenda.service.TarefaService;

@Service("tarefaService")
public class TarefaServiceImpl implements TarefaService{
	
	@Autowired
	private TarefaRepository tarefaRepository;

	@Override
	public List<Tarefa> findContatoByUser(Long id) {
		return tarefaRepository.findByUsuario(id);
	}
	

}
