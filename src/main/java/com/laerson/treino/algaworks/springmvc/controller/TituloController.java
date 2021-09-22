package com.laerson.treino.algaworks.springmvc.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.laerson.treino.algaworks.springmvc.model.StatusTitulo;
import com.laerson.treino.algaworks.springmvc.model.Titulo;
import com.laerson.treino.algaworks.springmvc.repository.TituloRepository;

@Controller
@RequestMapping("/titulos")
public class TituloController {

	@Autowired
	private TituloRepository tituloRepository;
	
	private static final String CADASTRO_VIEW = "CadastroTitulo";

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Titulo());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return "CADASTRO_VIEW";
		}

		tituloRepository.save(titulo);
		attributes.addFlashAttribute("mensagem", "Título salvo com sucesso!");
		return "redirect:/titulos/novo";
	}

//	@RequestMapping(method = RequestMethod.POST)
//	public ModelAndView salvar(@Validated Titulo titulo, Errors errors) {
//		ModelAndView mv = new ModelAndView("CadastroTitulo");
//		if (errors.hasErrors()) {
//			return mv;
//		}
//		
//		tituloRepository.save(titulo);
//		mv.addObject("mensagem", "Título salvo com sucesso!");
////	Opção para limpar os dados quando o título é salvo com sucesso -->	mv.addObject(new Titulo());
//		return mv;
//	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView pesquisar() {
		List<Titulo> todosTitulos = tituloRepository.findAll();

		ModelAndView mv = new ModelAndView("PesquisaTitulos");
		mv.addObject("titulos", todosTitulos);
		return mv;
	}
	
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Long codigoTitulo) {
		@SuppressWarnings("deprecation")
		Titulo titulo = tituloRepository.getOne(codigoTitulo);
		
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW); 
		mv.addObject(titulo);
		return mv;
	}

	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> listaDosStatus() {
		return Arrays.asList(StatusTitulo.values());
	}

}
