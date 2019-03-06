package br.inf.safetech.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {
	
//	TODO verificar a necessidade de pagina inicial e desenvolve-la ou não
	@RequestMapping("/")
	public String index() {
		System.out.println("entrando na home");
		
		return "home";
	}
	
		
}
