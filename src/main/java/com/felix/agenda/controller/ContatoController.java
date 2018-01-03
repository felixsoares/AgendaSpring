package com.felix.agenda.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.felix.agenda.model.Contato;
import com.felix.agenda.service.ContatoService;
import com.felix.agenda.service.TarefaService;
import com.felix.agenda.util.CustomModelAndView;

@Controller
@RequestMapping("/dashboard/contatos")
public class ContatoController extends DefaultController {

	@Autowired
	private ContatoService contatoService;

	@Autowired
	private TarefaService tarefaService;

	@GetMapping
	@Override
	public CustomModelAndView inicio() {
		CustomModelAndView modelAndView = new CustomModelAndView(userSession, usuarioService, "contato/contato-list");
		modelAndView.addObject("nomePagina", "Contatos");
		modelAndView.addObject("contatos", contatoService.findByUser(modelAndView.getUser().getId()));

		return modelAndView;
	}

	@GetMapping("/{id}/info")
	public CustomModelAndView remover(@PathVariable Long id) {
		Contato contato = contatoService.findById(id);

		CustomModelAndView modelAndView = new CustomModelAndView(userSession, usuarioService, "contato/contato-info");
		modelAndView.addObject("contato", contato);
		modelAndView.addObject("nomePagina", "Informações do Contato");
		modelAndView.addObject("tarefas", tarefaService.findByContato(modelAndView.getUser().getId(), contato.getId()));
		return modelAndView;
	}

	@GetMapping("/novo")
	public CustomModelAndView adicionar(Contato contato) {
		CustomModelAndView modelAndView = new CustomModelAndView(userSession, usuarioService, "contato/contato-form");
		modelAndView.addObject("contato", contato);
		modelAndView.addObject("nomePagina", "Cadastro de Contato");
		return modelAndView;
	}

	@PostMapping("/salvar")
	public CustomModelAndView salvar(@Valid Contato contato, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return adicionar(contato);
		}

		CustomModelAndView modelAndView = new CustomModelAndView(userSession, usuarioService, "redirect:/dashboard/contatos/");
		contato.setUsuario(modelAndView.getUser());

		contatoService.save(contato);

		attributes.addFlashAttribute("tipo", "success");
		attributes.addFlashAttribute("mensagem", "Contato salvo com sucesso!");

		return modelAndView;
	}

	@GetMapping("/editar/{id}")
	public CustomModelAndView editar(@PathVariable Long id) {
		return adicionar(contatoService.findById(id));
	}

	@DeleteMapping("/{id}")
	public ModelAndView excluir(@PathVariable Long id, RedirectAttributes attributes) {
		try {
			contatoService.delete(id);
			attributes.addFlashAttribute("tipo", "success");
			attributes.addFlashAttribute("mensagem", "Contato excluido com sucesso");
		} catch (Exception e) {
			attributes.addFlashAttribute("tipo", "danger");
			attributes.addFlashAttribute("mensagem", "Contato não pode ser excluído pois está vinculado a uma Tarefa");
		}

		return new ModelAndView("redirect:/dashboard/contatos/");
	}

}
