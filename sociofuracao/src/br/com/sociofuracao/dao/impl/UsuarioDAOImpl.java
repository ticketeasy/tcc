package br.com.sociofuracao.dao.impl;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import br.com.sociofuracao.dao.UsuarioDAO;
import br.com.sociofuracao.model.Usuario;

@Stateful
public class UsuarioDAOImpl implements UsuarioDAO{
	
	@PersistenceContext(unitName="sociofuracao",type= PersistenceContextType.EXTENDED)
    private EntityManager manager;

	@Override
	public Usuario login(String email, String senha) {
		// TODO Auto-generated method stub
		Query user = manager.createQuery("select e from Usuario e where e.email = :email and e.senha = :senha");
        user.setParameter("email", email);
        user.setParameter("senha",senha);
        Usuario user2=null;
        try{
          user.setHint(QueryHints.REFRESH, HintValues.TRUE);
          user2 = (Usuario) user.getSingleResult();
        }catch(Exception e){
            return null;
        }
        return user2;
	}

	@Override
	public void salvar(Usuario usuario) {
		// TODO Auto-generated method stub
		manager.merge(usuario);
		
	}

	@Override
	public Usuario findByCpf(String cpf) {
		// TODO Auto-generated method stub
		Query user = manager.createQuery("select e from Usuario e where e.cpf = :cpf");
		user.setParameter("cpf", cpf);
		Usuario user2=null;
		try{
		   user.setHint(QueryHints.REFRESH, HintValues.TRUE);
		   user2 = (Usuario) user.getSingleResult();
		}catch(Exception e){
		   return null;
		}
		return user2;
	}
	
	

}
