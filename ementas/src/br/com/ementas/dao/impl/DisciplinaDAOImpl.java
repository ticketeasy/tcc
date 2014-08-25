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

import br.com.ementas.dao.DisciplinaDAO;
import br.com.ementas.model.Disciplina;

@Stateful
public class DisciplinaDAOImpl implements DisciplinaDAO {
	
	@PersistenceContext(unitName="managerunit",type= PersistenceContextType.EXTENDED)
    private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Disciplina> lstDisciplina() {
		// TODO Auto-generated method stub
		List<Disciplina> lst = new ArrayList<Disciplina>();
		Query disciplina = manager.createQuery("select e from Disciplina e where e.ativo = 1");
	    try{
	    	disciplina.setHint(QueryHints.REFRESH, HintValues.TRUE); 
	    	lst = (List<Disciplina>) disciplina.getResultList();
        }catch(Exception e){
            return null;
        }
        return lst;
	}

	@Override
	public Disciplina getById(Long id) {
		// TODO Auto-generated method stub
		return manager.find(Disciplina.class, id);
	}

	@Override
	public void salvar(Disciplina disciplina) {
		// TODO Auto-generated method stub
		manager.merge(disciplina);
	}

}
