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

import br.com.ementas.dao.ProfessorDAO;
import br.com.ementas.model.Professor;

@Stateful
public class ProfessorDAOImpl implements ProfessorDAO {
	
	@PersistenceContext(unitName="managerunit",type= PersistenceContextType.EXTENDED)
    private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Professor> lstProfessores() {
		// TODO Auto-generated method stub
		List<Professor> lst = new ArrayList<Professor>();
		Query professores = manager.createQuery("select e from Professor e where e.ativo = 1");
	    try{
	    	professores.setHint(QueryHints.REFRESH, HintValues.TRUE);
	    	lst = (List<Professor>) professores.getResultList();
        }catch(Exception e){
            return null;
        }
        return lst;
	}

	@Override
	public Professor getById(Long id) {
		// TODO Auto-generated method stub
		return manager.find(Professor.class, id);
	}

	@Override
	public void salvar(Professor professor) {
		// TODO Auto-generated method stub
		manager.merge(professor);
	}

}
