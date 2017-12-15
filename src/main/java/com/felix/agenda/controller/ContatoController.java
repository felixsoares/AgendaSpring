package com.felix.agenda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.felix.agenda.model.Contato;
import com.felix.agenda.model.Usuario;
import com.felix.agenda.service.ContatoService;
import com.felix.agenda.service.TarefaService;

@Controller
@RequestMapping("/dashboard/contatos")
public class ContatoController extends DefaultController{

	@Autowired
	private ContatoService contatoService;
	
	@Autowired
	private TarefaService tarefaService;
	
	@GetMapping
	@Override
	public ModelAndView inicio() {
		Usuario user = userSession.getCurrentUser(usuarioService);
		List<Contato> contatos = contatoService.findAll();
		
		ModelAndView modelAndView = new ModelAndView("contato/contato-list");
		modelAndView.addObject("nomePagina", "Contatos");
		modelAndView.addObject("usuario", user);
		modelAndView.addObject("contatos", contatos);
		
		return modelAndView;
	}
	
	@GetMapping("/{id}/info")
	public ModelAndView remover(@PathVariable Long id) {
		Usuario user = userSession.getCurrentUser(usuarioService);
		Contato contato = contatoService.findById(id);
		
		ModelAndView modelAndView = new ModelAndView("contato/contato-info");
		modelAndView.addObject("contato", contato);
		modelAndView.addObject("nomePagina", "Informações do Contato");
		modelAndView.addObject("tarefas", tarefaService.findByContato(user.getId(), contato.getId()));
		modelAndView.addObject("usuario", user);
		return modelAndView;
	}

}
