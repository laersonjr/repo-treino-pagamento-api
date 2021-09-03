package com.laerson.treino.algaworks.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.laerson.treino.algaworks.springmvc.model.Titulo;
import com.laerson.treino.algaworks.springmvc.repository.TituloRepository;


@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	@Autowired
	private TituloRepository tituloRepository;
	
	@RequestMapping("/novo")
	public String novo() {
		return "CadastroTitulo";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(Titulo titulo) {
		tituloRepository.save(titulo);
		
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		mv.addObject("mensagem", "Título cadastrado com sucesso!");
		return mv;
	}

}
