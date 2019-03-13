package br.inf.safetech.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.inf.safetech.model.TipoUsuario;
import br.inf.safetech.model.Usuario;


@Controller
public class HomeController {
	
//	TODO verificar a necessidade de pagina inicial e desenvolve-la ou não
	@RequestMapping("/")
	public ModelAndView index(@AuthenticationPrincipal Usuario usuarioLogado) {
		if(usuarioLogado == null) {
			return new ModelAndView("redirect:/login");
		} 
		
		if(usuarioLogado.getTipo() == TipoUsuario.ADMIN) {
			return new ModelAndView("redirect:/admin");
		}
		
		if(usuarioLogado.getTipo() == TipoUsuario.COLABORADOR){
			return new ModelAndView("redirect:/colaborador");

		}
		
		
		return new ModelAndView("redirect:/login");
		
	}
	
		
}
