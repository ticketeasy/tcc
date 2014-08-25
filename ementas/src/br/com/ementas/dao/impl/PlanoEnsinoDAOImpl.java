package br.com.ementas.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import br.com.ementas.dao.PlanoEnsinoDAO;
import br.com.ementas.model.PlanoEnsino;

@Stateful
public class PlanoEnsinoDAOImpl implements PlanoEnsinoDAO {
	
	@PersistenceContext(unitName="managerunit",type= PersistenceContextType.EXTENDED)
    private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override
	public List<PlanoEnsino> lstPlanoEnsino() {
		// TODO Auto-generated method stub
		List<PlanoEnsino> lst = new ArrayList<PlanoEnsino>();
		Query planos = manager.createQuery("select e from PlanoEnsino e where e.ativo = 1");
		try{
			planos.setHint(QueryHints.REFRESH, HintValues.TRUE);
		   	lst = (List<PlanoEnsino>) planos.getResultList();
		}catch(Exception e){
		    return null;
		}
		return lst;
	}

	@Override
	public PlanoEnsino getById(Long id) {
		// TODO Auto-generated method stub
		return manager.find(PlanoEnsino.class, id);
	}

	@Override
	public void salvar(PlanoEnsino planoEnsino) {
		// TODO Auto-generated method stub
		manager.merge(planoEnsino);
	}

}
