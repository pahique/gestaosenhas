package processoseletivo.gestaosenhas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import processoseletivo.gestaosenhas.model.TipoUsuario;

@Repository
public class TipoUsuarioDao implements Dao<TipoUsuario> {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public TipoUsuario get(long id) {
		return em.find(TipoUsuario.class, id);
	}

	@Override
	public List<TipoUsuario> getAll() {
        TypedQuery<TipoUsuario> query = em.createQuery("SELECT e FROM TipoUsuario e", TipoUsuario.class);
        return query.getResultList();
	}

	@Override
	public void save(TipoUsuario obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(TipoUsuario obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(TipoUsuario obj) {
		// TODO Auto-generated method stub
		
	}

}
