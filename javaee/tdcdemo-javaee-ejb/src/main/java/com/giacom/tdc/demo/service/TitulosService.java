package com.giacom.tdc.demo.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.giacom.tdc.demo.data.TitulosRepository;
import com.giacom.tdc.demo.model.Titulo;
import com.giacom.tdc.demo.model.TituloFilter;

/**
 * Session Bean implementation class TitulosService
 */
@Stateless
@LocalBean
public class TitulosService {

    @Inject
    private TitulosRepository repository;
   
    //@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<Titulo> filtrar(TituloFilter filtro) throws Exception {
		return repository.filtrar(filtro);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public String receber(Titulo titulo) throws Exception {

		System.out.println("Recebendo titulo: " + titulo.getCodigo());
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {}
		
		List<Titulo> list = filtrar(new TituloFilter());
		System.out.println("list.size() = " + list.size());
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {}
		
		return repository.receber(titulo);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void excluir(Titulo titulo) throws Exception {
		System.out.println("Excluindo titulo: " + titulo.getCodigo());
    	repository.excluir(titulo);
    }

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Titulo salvar(Titulo titulo) {
    	return repository.salvar(titulo);
    }

}
