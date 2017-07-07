package com.giacom.tdc.demo.service;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.giacom.tdc.demo.data.TitulosRepository;
import com.giacom.tdc.demo.model.Titulo;
import com.giacom.tdc.demo.model.TituloFilter;

/**
 * Session Bean implementation class TitulosService
 */
@Stateless
@LocalBean
@PermitAll
public class TitulosService {

    @Inject
    private TitulosRepository repository;
   
	public List<Titulo> filtrar(TituloFilter filtro) throws Exception {
		return repository.filtrar(filtro);
	}

	public String receber(Titulo titulo) throws Exception {
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		List<Titulo> list = filtrar(new TituloFilter());
		
		System.out.println(list.size());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return repository.receber(titulo);
	}
	
    public void excluir(Titulo titulo) throws Exception {
    	repository.excluir(titulo);
    }

	
    public Titulo salvar(Titulo titulo) {
    	return repository.salvar(titulo);
    }

}
