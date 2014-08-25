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

import br.com.ementas.dao.CursoDAO;
import br.com.ementas.model.Curso;

@Stateful
public class CursoDAOImpl implements CursoDAO{
	
	@PersistenceContext(unitName="managerunit",type= PersistenceContextType.EXTENDED)
    private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Curso> lstCurso() {
		// TODO Auto-generated method stub
		List<Curso> lst = new ArrayList<Curso>();
		Query cursos = manager.createQuery("select e from Curso e where e.ativo = 1");
	    try{
	    	cursos.setHint(QueryHints.REFRESH, HintValues.TRUE);
	    	lst = (List<Curso>) cursos.getResultList();
        }catch(Exception e){
            return null;
        }
        return lst;
	}

	@Override
	public Curso getById(Long id) {
		// TODO Auto-generated method stub
		Curso ret=null;
		Query curso = manager.createQuery("select e from Curso e where e.id = :id and e.ativo = 1");
		
	    try{
	    	curso.setParameter("id", id);
	    	curso.setHint(QueryHints.REFRESH, HintValues.TRUE);
	    	ret = (Curso) curso.getSingleResult();
	    }catch(Exception e){
	         return null;
	    }
	    return ret;
	}

	@Override
	public void salvar(Curso curso) {
		// TODO Auto-generated method stub
		manager.merge(curso);
	}

}
