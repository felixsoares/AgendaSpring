package com.felix.agenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.felix.agenda.model.Usuario;
import com.felix.agenda.service.UsuarioService;
import com.felix.agenda.util.CustomModelAndView;

@Controller
@RequestMapping("/dashboard/profile")
public class ProfileController extends DefaultController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	@Override
	public CustomModelAndView inicio() {
		CustomModelAndView modelAndView = new CustomModelAndView(userSession, usuarioService, "profile/profile-form");
		modelAndView.addObject("nomePagina", "Profile");
		return modelAndView;
	}

	@PostMapping("/salvar")
	public CustomModelAndView salvar(Usuario usuario, BindingResult result, RedirectAttributes attributes) {
		boolean hasError = false;
		CustomModelAndView modelAndView = new CustomModelAndView(userSession, usuarioService);
		modelAndView.addObject("nomePagina", "Profile");

		Usuario user = usuarioService.findUserById(usuario.getId());

		if (usuario.getSenha() == null || usuario.getSenha().trim().equals("")) {
			usuario.setNecessarioEncriptarSenha(false);
			usuario.setSenha(user.getSenha());
		} else {
			usuario.setNecessarioEncriptarSenha(true);
		}

		if (usuario.getUsername() == null || usuario.getUsername().trim().equals("")) {
			usuario.setUsername(user.getUsername());
		}

		if (usuario.getNome() == null || usuario.getNome().trim().equals("")) {
			hasError = true;
			attributes.addFlashAttribute("tipo", "success");
			attributes.addFlashAttribute("mensagem", "Nome obrigat\\u00f3rio");
		}

		if (hasError) {
			modelAndView.setViewName("redirect:/dashboard/profile/");
		} else {
			usuarioService.salvar(usuario);
			modelAndView.setViewName("redirect:/dashboard/profile/");
			attributes.addFlashAttribute("tipo", "success");
			attributes.addFlashAttribute("mensagem", "Usu\u00e1rio alterado com sucesso, faça login novamente =)");
		}

		return modelAndView;
	}
}
