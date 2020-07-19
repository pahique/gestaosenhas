package processoseletivo.gestaosenhas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import processoseletivo.gestaosenhas.model.TipoSenha;

@Repository
public class TipoSenhaDao implements Dao<TipoSenha> {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public TipoSenha get(long id) {
		return em.find(TipoSenha.class, id);
	}

	@Override
	public List<TipoSenha> getAll() {
        TypedQuery<TipoSenha> query = em.createQuery("SELECT e FROM TipoSenha e", TipoSenha.class);
        return query.getResultList();
	}

	@Override
	public void save(TipoSenha obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(TipoSenha obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(TipoSenha obj) {
		// TODO Auto-generated method stub
		
	}

}
