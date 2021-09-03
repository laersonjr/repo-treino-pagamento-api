package com.laerson.treino.algaworks.springmvc.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.laerson.treino.algaworks.springmvc.model.StatusTitulo;
import com.laerson.treino.algaworks.springmvc.model.Titulo;
import com.laerson.treino.algaworks.springmvc.repository.TituloRepository;


@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	@Autowired
	private TituloRepository tituloRepository;
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		mv.addObject("todosStatusTitulo", StatusTitulo.values());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(Titulo titulo) {
		tituloRepository.save(titulo);
		
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		mv.addObject("mensagem", "Título cadastrado com sucesso!");
		return mv;
	}
	
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> listaDosStatus(){
		return Arrays.asList(StatusTitulo.values());
	}

}
