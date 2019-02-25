package br.inf.safetech.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/colaborador")
public class ColaboradorController {

	
	@RequestMapping("")
	public ModelAndView usuarioOverview() {
		ModelAndView modelAndView = new ModelAndView("colaborador/geral");
//		TODO mostrar todas as contas do usuario 
		
		return modelAndView;
	}
	
	@RequestMapping("conta/{id}")
	public ModelAndView conta(@PathVariable("id") Integer id) {
//		TODO mostrar detalhes da conta selecionada
		
		return null;
	}
}
