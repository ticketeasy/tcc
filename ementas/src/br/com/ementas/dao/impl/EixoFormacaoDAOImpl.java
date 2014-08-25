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

import br.com.ementas.dao.EixoFormacaoDAO;
import br.com.ementas.model.EixoFormacao;

@Stateful
public class EixoFormacaoDAOImpl implements EixoFormacaoDAO{
	
	@PersistenceContext(unitName="managerunit",type= PersistenceContextType.EXTENDED)
    private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override
	public List<EixoFormacao> lstEixoFormacao() {
		// TODO Auto-generated method stub
		List<EixoFormacao> lst = new ArrayList<EixoFormacao>();
		Query eixos = manager.createQuery("select e from EixoFormacao e where e.ativo = 1");
	    try{
	    	eixos.setHint(QueryHints.REFRESH, HintValues.TRUE);
	    	lst = (List<EixoFormacao>) eixos.getResultList();
        }catch(Exception e){
            return null;
        }
        return lst;
	}

	@Override
	public void salvar(EixoFormacao eixo) {
		// TODO Auto-generated method stub
		manager.merge(eixo);
	}

	@Override
	public EixoFormacao getById(Long id) {
		// TODO Auto-generated method stub
		return manager.find(EixoFormacao.class, id);
	}

}
