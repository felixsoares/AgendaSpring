package com.felix.agenda.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.felix.agenda.model.Tarefa;
import com.felix.agenda.repository.TarefaRepository;
import com.felix.agenda.service.TarefaService;

@Service("tarefaService")
public class TarefaServiceImpl implements TarefaService{
	
	@Autowired
	private TarefaRepository tarefaRepository;

	@Override
	public List<Tarefa> findByUser(Long id) {
		return tarefaRepository.findByUsuario(id, new PageRequest(0, 10));
	}

	@Override
	public List<Tarefa> findByContato(Long id, Long idContato) {
		return tarefaRepository.findByContato(id, idContato);
	}

	@Override
	public List<Tarefa> findAll() {
		return tarefaRepository.findAll();
	}

	@Override
	public Tarefa findById(Long id) {
		return tarefaRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		tarefaRepository.delete(id);
	}

	@Override
	public void save(Tarefa tarefa) {
		tarefaRepository.save(tarefa);
	}

	@Override
	public void marcar(Long id) {
		Tarefa tarefa = tarefaRepository.findOne(id);
		tarefa.setCheck(!tarefa.isCheck());
		
		save(tarefa);
	}

}
