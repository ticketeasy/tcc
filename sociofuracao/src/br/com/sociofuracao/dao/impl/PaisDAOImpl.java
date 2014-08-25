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

import br.com.sociofuracao.dao.PaisDAO;
import br.com.sociofuracao.model.Pais;

@Stateful
public class PaisDAOImpl implements PaisDAO {
	
	@PersistenceContext(unitName="sociofuracao",type= PersistenceContextType.EXTENDED)
    private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Pais> getPaises() {
		// TODO Auto-generated method stub
		Query qy = manager.createQuery("select e from Pais e");
       
        List<Pais> paises = new ArrayList<Pais>();
        try{
        	qy.setHint(QueryHints.REFRESH, HintValues.TRUE);
        	paises = (List<Pais>) qy.getResultList();
        }catch(Exception e){
            return null;
        }
        return paises;
		
	}

	@Override
	public Pais findBySigla(String sigla) {
		// TODO Auto-generated method stub
		Query qy = manager.createQuery("select e from Pais e where e.sigla = :sigla");
		qy.setParameter("sigla", sigla);
		Pais pais = null;
		try{
			qy.setHint(QueryHints.REFRESH, HintValues.TRUE);
			pais = (Pais) qy.getSingleResult();
		}catch(Exception e){
			return null;
		}
		return pais;
	}

}
