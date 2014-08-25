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

import br.com.ementas.dao.UsuarioDAO;
import br.com.ementas.model.Usuario;


@Stateful
public class UsuarioDAOImpl implements UsuarioDAO {
	
	@PersistenceContext(unitName="managerunit",type= PersistenceContextType.EXTENDED)
    private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> lstUsuarios() {
		// TODO Auto-generated method stub
		List<Usuario> lst = new ArrayList<Usuario>();
		Query usuarios = manager.createQuery("select e from Usuario e where e.ativo = 1");
	    try{
	    	usuarios.setHint(QueryHints.REFRESH, HintValues.TRUE);
	    	lst = (List<Usuario>) usuarios.getResultList();
        }catch(Exception e){
            return null;
        }
        return lst;
	}

	@Override
	public Usuario findByUsuario(String usuario, String senha) {
		
		Query user = manager.createQuery("select e from Usuario e where e.usuario = :usuario and e.senha = :senha and e.ativo = '1'");
        user.setParameter("usuario", usuario);
        user.setParameter("senha",senha);
        Usuario user2=null;
        try{
            
          user2 = (Usuario) user.getSingleResult();
        
        }catch(Exception e){
            return null;
        }
        return user2;
	}

	@Override
	public Usuario getById(Long id) {
		// TODO Auto-generated method stub
		return manager.find(Usuario.class, id);
	}

	@Override
	public void salvar(Usuario usuario) {
		// TODO Auto-generated method stub
		manager.merge(usuario);
	}
	
	

}
