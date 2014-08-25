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

import br.com.ementas.dao.PerfilDAO;
import br.com.ementas.model.Perfil;

@Stateful
public class PerfilDAOImpl implements PerfilDAO{
	
	@PersistenceContext(unitName="managerunit",type= PersistenceContextType.EXTENDED)
    private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Perfil> lstPerfis() {
		// TODO Auto-generated method stub
		List<Perfil> lstPerfis = new ArrayList<Perfil>();
		Query perfis = manager.createQuery("select e from Perfil e where e.ativo = '1'");
	    try{
	    	perfis.setHint(QueryHints.REFRESH, HintValues.TRUE);
          lstPerfis = (List<Perfil>) perfis.getResultList();
        }catch(Exception e){
            return null;
        }
        return lstPerfis;
		
	}
	
	@Override
	public Perfil getById(Long id) {
		// TODO Auto-generated method stub
		return manager.find(Perfil.class, id);
	}

	@Override
	public void salvar(Perfil perfil) {
		// TODO Auto-generated method stub
		manager.merge(perfil);
	}
	
	@Override
	public Perfil findById(Long id) {
		// TODO Auto-generated method stub
		Perfil perfil = null;
		Query qy = manager.createQuery("select e from Perfil e where e.idPerfil = :id");
		qy.setParameter("id", id);
	    try{
	    	qy.setHint(QueryHints.REFRESH, HintValues.TRUE);
          perfil = (Perfil) qy.getSingleResult();
        }catch(Exception e){
            return null;
        }
        return perfil;
		
	}

}
