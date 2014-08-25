package br.com.sociofuracao.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import br.com.sociofuracao.dao.LugarDAO;
import br.com.sociofuracao.model.Lugar;

@Stateful
public class LugarDAOImpl implements LugarDAO {
	
	@PersistenceContext(unitName="sociofuracao",type= PersistenceContextType.EXTENDED)
    private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Lugar> lstLugar(String email) {
		Query qy = manager.createQuery("select e from Lugar e where e.usuario.email = :email");
		qy.setParameter("email", email);
		List<Lugar> lugares = new ArrayList<Lugar>();
		try{
			qy.setHint(QueryHints.REFRESH, HintValues.TRUE);
			lugares = (List<Lugar>) qy.getResultList();
		}catch(Exception e){
			return null;
		}
		return lugares;
	}

	@Override
	public Lugar findById(Long id) {
		Query qy = manager.createQuery("select e from Lugar e where e.id = :id");
		qy.setParameter("id", id);
		Lugar lugar = null;
		try{
			qy.setHint(QueryHints.REFRESH, HintValues.TRUE);
			lugar = (Lugar) qy.getSingleResult();
		}catch(Exception e){
			return null;
		}
		return lugar;
	}

	@Override
	public void salvar(Lugar lugar) {
		// TODO Auto-generated method stub
		manager.merge(lugar);
		manager.flush();
	}

}
