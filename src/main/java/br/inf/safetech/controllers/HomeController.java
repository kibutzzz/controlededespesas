package br.inf.safetech.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
	
	
	@RequestMapping("/")
	public String index() {
		System.out.println("entrando na home");
		
		return "home";
	}
	
	@RequestMapping("/login")
	public String login() {
		System.out.println("Entrando no login");
		return "login";
	}
	
	
	@RequestMapping("/login/autentica")
	public String autenticar(String login, String senha) {
		System.out.println(login);
		System.out.println(senha);
		
		return "redirect:../";
	}
	
}
