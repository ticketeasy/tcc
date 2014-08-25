package br.com.sociofuracao.dao.impl;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import br.com.sociofuracao.dao.CidadeDAO;
import br.com.sociofuracao.model.Cidade;

@Stateful
public class CidadeDAOImpl implements CidadeDAO {
	
	@PersistenceContext(unitName="sociofuracao",type= PersistenceContextType.EXTENDED)
    private EntityManager manager;

	@Override
	public Cidade findByCidade(String nome) {
		Query qy = manager.createQuery("select e from Cidade e where e.nome = :nome");
		qy.setParameter("nome", nome);
	    Cidade cidade = null;
        try{
        	cidade = (Cidade) qy.getSingleResult();
        }catch(Exception e){
            return null;
        }
        return cidade;
	}
	
	

}
