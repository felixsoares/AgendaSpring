package com.felix.agenda.service;

import java.util.List;

import com.felix.agenda.model.Contato;

public interface ContatoService {
	public List<Contato> findContatoByUser(Long id);
	public List<Contato> findAll();
	public Contato findById(Long id);
	public void save(Contato contato);
}
