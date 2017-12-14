package com.felix.agenda.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felix.agenda.model.Contato;
import com.felix.agenda.repository.ContatoRepository;
import com.felix.agenda.service.ContatoService;

@Service("contatoService")
public class ContatoServiceImpl implements ContatoService{
	
	@Autowired
	private ContatoRepository contatoRepository;

	@Override
	public List<Contato> findContatoByUser(Long id) {
		return contatoRepository.findByUsuario(id);
	}
	

}
