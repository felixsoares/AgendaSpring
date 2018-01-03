package com.felix.agenda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.felix.agenda.model.Contato;
import com.felix.agenda.model.Tarefa;
import com.felix.agenda.service.ContatoService;
import com.felix.agenda.service.TarefaService;
import com.felix.agenda.util.CustomModelAndView;

@Controller
@RequestMapping("/dashboard")
public class DashboardController extends DefaultController{

	@Autowired
	private ContatoService contatoService;

	@Autowired
	private TarefaService tarefaService;
	
	@GetMapping
	@Override
	public CustomModelAndView inicio() {
		CustomModelAndView modelAndView = new CustomModelAndView(userSession, usuarioService, "dashboard/dashboard");
		
		List<Contato> contatos = contatoService.findByUser(modelAndView.getUser().getId());
		List<Tarefa> tarefas = tarefaService.findByUser(modelAndView.getUser().getId());
		
		modelAndView.addObject("nomePagina", "Dashbaord");
		modelAndView.addObject("contatos", contatos);
		modelAndView.addObject("tarefas", tarefas);
		
		return modelAndView;
	}

}
