package com.felix.agenda.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.felix.agenda.model.Tarefa;
import com.felix.agenda.service.ContatoService;
import com.felix.agenda.service.TarefaService;
import com.felix.agenda.util.CustomModelAndView;

@Controller
@RequestMapping("/dashboard/tarefas")
public class TarefaController extends DefaultController {

	@InitBinder
    public void initBinder (WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy HH:mm"), true));
    }
	
	@Autowired
	private ContatoService contatoService;
	
	@Autowired
	private TarefaService tarefaService;

	@GetMapping
	@Override
	public CustomModelAndView inicio() {
		CustomModelAndView modelAndView = new CustomModelAndView(userSession, usuarioService,"tarefa/tarefa-list");
		List<Tarefa> tarefas = tarefaService.findByUser(modelAndView.getUser().getId());
		modelAndView.addObject("nomePagina", "Tarefas");
		modelAndView.addObject("tarefas", tarefas);

		return modelAndView;
	}

	@GetMapping("/novo")
	public CustomModelAndView adicionar(Tarefa tarefa) {
		CustomModelAndView modelAndView = new CustomModelAndView(userSession, usuarioService, "tarefa/tarefa-form");
		modelAndView.addObject("tarefa", tarefa);
		modelAndView.addObject("contatosList", contatoService.findByUser(modelAndView.getUser().getId()));
		modelAndView.addObject("nomePagina", "Cadastro de Tarefa");
		return modelAndView;
	}

	@PostMapping("/salvar")
	public CustomModelAndView salvar(@Valid Tarefa tarefa, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return adicionar(tarefa);
		}

		CustomModelAndView modelAndView = new CustomModelAndView(userSession, usuarioService, "redirect:/dashboard/tarefas/");
		tarefa.setUsuario(modelAndView.getUser());

		tarefaService.save(tarefa);

		attributes.addFlashAttribute("tipo", "success");
		attributes.addFlashAttribute("mensagem", "Tarefa salva com sucesso!");

		return modelAndView;
	}

	@GetMapping("/editar/{id}")
	public CustomModelAndView editar(@PathVariable Long id) {
		return adicionar(tarefaService.findById(id));
	}
	
	@GetMapping("/marcar/{id}")
	public ModelAndView marcar(@PathVariable Long id, RedirectAttributes attributes) {
		try {
			tarefaService.marcar(id);
			attributes.addFlashAttribute("tipo", "success");
			attributes.addFlashAttribute("mensagem", "Operação realizada com sucesso!");
		} catch (Exception e) {
			attributes.addFlashAttribute("tipo", "danger");
			attributes.addFlashAttribute("mensagem", "Algum erro aconteceu! =(");
		}
		
		return new ModelAndView("redirect:/dashboard/tarefas/");
	}
	
	@DeleteMapping("/{id}")
	public ModelAndView excluir(@PathVariable Long id, RedirectAttributes attributes) {
		try {
			tarefaService.delete(id);
			attributes.addFlashAttribute("tipo", "success");
			attributes.addFlashAttribute("mensagem", "Tarefa excluida com sucesso");
		} catch (Exception e) {
			attributes.addFlashAttribute("tipo", "danger");
			attributes.addFlashAttribute("mensagem", "Algum erro aconteceu! =(");
		}

		return new ModelAndView("redirect:/dashboard/tarefas/");
	}
	
	@GetMapping("/{id}/info")
	public CustomModelAndView info(@PathVariable Long id) {
		CustomModelAndView modelAndView = new CustomModelAndView(userSession, usuarioService, "tarefa/tarefa-info");
		modelAndView.addObject("tarefa", tarefaService.findById(id));
		modelAndView.addObject("nomePagina", "Detalhe da Tarefa");
		return modelAndView;
	}

}
