package com.felix.agenda.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.felix.agenda.model.Usuario;
import com.felix.agenda.service.UsuarioService;

@Controller
@RequestMapping(value={"/", "/login"})
public class LoginController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ModelAndView inicio() {
		ModelAndView modelAndView = new ModelAndView("login/login");
		modelAndView.addObject("usuario", new Usuario());
		return modelAndView;
	}
	
	@GetMapping("/cadastrarse")
	public ModelAndView cadastrarse(){
		ModelAndView modelAndView = new ModelAndView("login/cadastro");
		modelAndView.addObject("usuario", new Usuario());
		return modelAndView;
	}

	@PostMapping("/cadastrarse")
	public ModelAndView login(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {
		ModelAndView modelAndView = new ModelAndView();
	
		Usuario user = usuarioService.findUserByEmail(usuario.getUsername());
		
		if(user != null) {
			result.rejectValue("username", "error.user", "Usu\u00e1rio j\u00e1 cadastrado no sistema");
		}
		
		if(result.hasErrors()) {
			modelAndView.setViewName("login/cadastro");
		}else {
			usuarioService.salvar(usuario);
			modelAndView.setViewName("redirect:/login");
			attributes.addFlashAttribute("mensagem", "Usu\u00e1rio inserido com sucesso, faça login para usar =)");
		}
	
		return modelAndView;
	}
	
	@GetMapping(value="/dashbaord")
	public ModelAndView home(){
		return new ModelAndView("dashbaord");
	}

}
