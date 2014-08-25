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

import br.com.sociofuracao.dao.TipoUsuarioDAO;
import br.com.sociofuracao.model.TipoUsuario;

@Stateful
public class TipoUsuarioDAOImpl implements TipoUsuarioDAO {

	@PersistenceContext(unitName = "sociofuracao", type = PersistenceContextType.EXTENDED)
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoUsuario> findAll() {
		// TODO Auto-generated method stub
		Query qy = manager
				.createQuery("select e from TipoUsuario e");
		List<TipoUsuario> lst = new ArrayList<TipoUsuario>();
		try {
			qy.setHint(QueryHints.REFRESH, HintValues.TRUE);
			lst = (List<TipoUsuario>) qy.getResultList();
		} catch (Exception e) {
			return null;
		}
		return lst;
	}

	@Override
	public TipoUsuario findById(Long id) {
		// TODO Auto-generated method stub
		TipoUsuario tipo = null;
		try{
			tipo = manager.find(TipoUsuario.class, id);
		}catch(Exception e){
			return null;
		}
		return tipo;
	}

}
