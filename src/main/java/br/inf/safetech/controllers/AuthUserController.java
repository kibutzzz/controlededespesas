package br.inf.safetech.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.inf.safetech.helper.StatusInfo;
import br.inf.safetech.helper.StatusType;

@Controller
public class AuthUserController {
	
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView loginForm() {
		
	
		return new ModelAndView("login");
	}
	
	@RequestMapping("/acessoNegado")
	public ModelAndView acessoNegado(RedirectAttributes redirectAtriAttributes) {
		
		redirectAtriAttributes.addFlashAttribute("status", new StatusInfo(StatusType.ALERTA, 
			"Você não tem permissão para logar nesta página. Faça login para poder acessá-la."));
		
		return new ModelAndView("acessoNegado");
	}

}
