package com.giacom.tdc.demo.data;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.giacom.tdc.demo.model.StatusTitulo;
import com.giacom.tdc.demo.model.Titulo;
import com.giacom.tdc.demo.model.TituloFilter;

public class TitulosRepository {

    @Inject
    private EntityManager em;

    
    public void excluir(Titulo titulo) throws Exception {
    	Titulo t = em.find(Titulo.class, titulo.getCodigo());
    	if(t == null){
    		throw new Exception("Titulo [" + titulo.getCodigo() + "] not found!" );
    	}
    	em.remove(t);
	}

	public String receber(Titulo titulo) {

		Titulo t = em.find(Titulo.class, titulo.getCodigo());
		t.setStatus(StatusTitulo.RECEBIDO);
		em.merge(t);
	
		return StatusTitulo.RECEBIDO.getDescricao();
	}
	
	@SuppressWarnings("unchecked")
	public List<Titulo> filtrar(TituloFilter filtro) throws Exception {
		String descricao = (filtro.getDescricao() == null || filtro.getDescricao().isEmpty()) ? "%" : "%'" + filtro.getDescricao() + "'%";
		Query q = em.createQuery("select t from Titulo t where t.descricao like :descricao order by t.codigo");
		q.setParameter("descricao", descricao);
		List<Titulo> result = Collections.emptyList();
		try {
			result = q.getResultList();
		} catch (Exception e) {
			throw new Exception("Erro ao filtrar. ", e);
		} 
		
		return result;
	}

	public Titulo salvar(Titulo titulo) {
		if(titulo.getCodigo() != null && titulo.getCodigo() > 0){
			em.merge(titulo);
			System.out.println("Titulo salvo: " + titulo.getCodigo());
		}else{
			titulo.setCodigo(null);
			em.persist(titulo);
			System.out.println("Titulo criado: " + titulo.getCodigo());
		}
		
		return titulo;
	}
    
}
