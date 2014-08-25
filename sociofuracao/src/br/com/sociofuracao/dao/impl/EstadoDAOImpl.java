package br.com.sociofuracao.dao.impl;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import br.com.sociofuracao.dao.EstadoDAO;
import br.com.sociofuracao.model.Estado;

@Stateful
public class EstadoDAOImpl implements EstadoDAO {
	
	@PersistenceContext(unitName="sociofuracao",type= PersistenceContextType.EXTENDED)
    private EntityManager manager;

	@Override
	public Estado findBySigla(String sigla){
		// TODO Auto-generated method stub
		Query qy = manager.createQuery("select e from Estado e where e.sigla = :sigla");
		qy.setParameter("sigla", sigla);
	    Estado estado = null;
        try{
        	qy.setHint(QueryHints.REFRESH, HintValues.TRUE);
        	estado = (Estado) qy.getSingleResult();
        }catch(Exception e){
            return null;
        }
        return estado;
	}


}
