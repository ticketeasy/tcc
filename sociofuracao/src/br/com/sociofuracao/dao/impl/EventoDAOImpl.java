package br.com.sociofuracao.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import br.com.sociofuracao.dao.EventoDAO;
import br.com.sociofuracao.model.Evento;

@Stateful
public class EventoDAOImpl implements EventoDAO {
	
	@PersistenceContext(unitName="sociofuracao",type= PersistenceContextType.EXTENDED)
    private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Evento> findAll() {
		Query qy = manager.createQuery("select e from Evento e");
		List<Evento> lst = new ArrayList<Evento>();
        try{
        	qy.setHint(QueryHints.REFRESH, HintValues.TRUE);
        	lst = (List<Evento>) qy.getResultList();
        }catch(Exception e){
            return null;
        }
        return lst;
	}

	@Override
	public void salvar(Evento evento) {
		// TODO Auto-generated method stub
		manager.merge(evento);
		manager.flush();
	}
	

	@Override
	public Evento findById(Long id) {
		Query qy = manager.createQuery("select e from Evento e where e.id = :id");
		qy.setParameter("id", id);
		Evento evento = null;
		try{
			qy.setHint(QueryHints.REFRESH, HintValues.TRUE);
			evento = (Evento) qy.getSingleResult();
		}catch(Exception e){
			return null;
		}
		return evento;
	}

	@Override
	public void remove(Evento evento) {
		// TODO Auto-generated method stub
		manager.remove(evento);
	}

	@Override
	public List<Evento> getAll() {
		// TODO Auto-generated method stub
		List<Evento> eventos = new ArrayList<Evento>();
		try{
		  CriteriaBuilder cb = manager.getCriteriaBuilder();
		  CriteriaQuery<Evento> cq = cb.createQuery(Evento.class);
		  Root<Evento> evento = cq.from(Evento.class);	
		  cq.select(evento);
		  cq.orderBy(cb.desc(evento.get("data")),cb.desc(evento.get("hora")));
		  TypedQuery<Evento> query = manager.createQuery(cq);
		
		  eventos = query.getResultList();
		}catch(Exception e){
			return null;
		}
		
		return eventos;
	}

	@Override
	public Evento getById(Long id) {
		// TODO Auto-generated method stub
		Evento evento = null;
		try{
			CriteriaBuilder cb = manager.getCriteriaBuilder();
			CriteriaQuery<Evento> cq = cb.createQuery(Evento.class);
			Root<Evento> ev = cq.from(Evento.class);
			cq.select(ev);
			cq.where(cb.equal(ev.get("id"), id));
			TypedQuery<Evento> query = manager.createQuery(cq);
			evento = query.getSingleResult();
		}catch(Exception e){
			return null;
		}
		return evento;
	}


}
