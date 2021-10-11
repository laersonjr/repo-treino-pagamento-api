package com.laerson.treino.algaworks.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.laerson.treino.algaworks.springmvc.model.StatusTitulo;
import com.laerson.treino.algaworks.springmvc.model.Titulo;
import com.laerson.treino.algaworks.springmvc.repository.TituloRepository;


@Service
public class CadastroTituloService {

	@Autowired
	private TituloRepository tituloRepository;

	public void salvar(Titulo titulo) {
		try {
			tituloRepository.save(titulo);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato de data inválido");
		}
	}
	
	public void excluir(Long codigo) {
		tituloRepository.deleteById(codigo);
	}

	public String receber(Long codigo) {
		Titulo titulo = tituloRepository.getOne(codigo);
		titulo.setStatus(StatusTitulo.RECEBIDO);
		tituloRepository.save(titulo);
		
		return StatusTitulo.RECEBIDO.getDescricao();
	}

}
