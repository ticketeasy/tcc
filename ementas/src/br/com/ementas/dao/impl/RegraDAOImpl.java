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

import br.com.ementas.dao.RegraDAO;
import br.com.ementas.model.Regra;

@Stateful
public class RegraDAOImpl implements RegraDAO {
	
	@PersistenceContext(unitName="managerunit",type= PersistenceContextType.EXTENDED)
    private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Regra> lstRegras() {
		// TODO Auto-generated method stub
		List<Regra> lstRegras = new ArrayList<Regra>();
		Query regras = manager.createQuery("select e from Regra e");
	    try{
	    	regras.setHint(QueryHints.REFRESH, HintValues.TRUE);
	    	lstRegras = (List<Regra>) regras.getResultList();
        }catch(Exception e){
            return null;
        }
        return lstRegras;
	}
	
	@Override
	public Regra getNome(String desc) {
		// TODO Auto-generated method stub
		Regra regra = new Regra();
		Query regras = manager.createQuery("select e from Regra e where e.nomeRegra = :desc");
		regras.setParameter("desc", desc);
	    try{
	    	regras.setHint(QueryHints.REFRESH, HintValues.TRUE);
	    	regra = (Regra) regras.getSingleResult();
        }catch(Exception e){
            return null;
        }
        return regra;
	}

	@Override
	public Regra getById(Long id) {
		// TODO Auto-generated method stub
		return manager.find(Regra.class, id);
	}

	@Override
	public void salvar(Regra regra) {
		// TODO Auto-generated method stub
		manager.merge(regra);
	}

}
