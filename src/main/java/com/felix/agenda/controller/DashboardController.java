package com.felix.agenda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.felix.agenda.model.Contato;
import com.felix.agenda.model.Tarefa;
import com.felix.agenda.model.Usuario;
import com.felix.agenda.service.ContatoService;
import com.felix.agenda.service.TarefaService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController extends DefaultController{

	@Autowired
	private ContatoService contatoService;

	@Autowired
	private TarefaService tarefaService;
	
	@GetMapping
	public ModelAndView inicio() {
		Usuario user = userSession.getCurrentUser(usuarioService);
		List<Contato> contatos = contatoService.findContatoByUser(user.getId());
		List<Tarefa> tarefas = tarefaService.findContatoByUser(user.getId());
		
		ModelAndView modelAndView = new ModelAndView("dashboard/dashboard");
		modelAndView.addObject("nomePagina", "Dashbaord");
		modelAndView.addObject("usuario", user);
		modelAndView.addObject("contatos", contatos);
		modelAndView.addObject("tarefas", tarefas);
		
		return modelAndView;
	}

}
