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

import br.com.sociofuracao.dao.EstadioDAO;
import br.com.sociofuracao.model.Estadio;

@Stateful
public class EstadioDAOImpl implements EstadioDAO {

	@PersistenceContext(unitName = "sociofuracao", type = PersistenceContextType.EXTENDED)
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Estadio> findAll() {
		Query qy = manager.createQuery("select e from Estadio e");
		List<Estadio> lst = new ArrayList<Estadio>();
		try {
			qy.setHint(QueryHints.REFRESH, HintValues.TRUE);
			lst = (List<Estadio>) qy.getResultList();
		} catch (Exception e) {
			return null;
		}
		return lst;

	}

	@Override
	public Estadio findById(Long id) {
		Estadio estadio = null;
		try{
			estadio = manager.find(Estadio.class, id);
		}catch(Exception e){
			return null;
		}
		return estadio;
	}

}
