package processoseletivo.gestaosenhas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import processoseletivo.gestaosenhas.model.SenhaEmitida;

@Repository
public class SenhaEmitidaDao implements Dao<SenhaEmitida> {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public SenhaEmitida get(long id) {
		return em.find(SenhaEmitida.class, id);
	}

	public List<SenhaEmitida> findByTipoSenha(int tipoSenha) {
        TypedQuery<SenhaEmitida> query = em.createQuery("SELECT e FROM SenhaEmitida e where e.tipoSenha.codigo = :codigo orderby e.timestamp", SenhaEmitida.class);
        query.setParameter("codigo", tipoSenha);
        return query.getResultList();
	}

	@Override
	public List<SenhaEmitida> getAll() {
        TypedQuery<SenhaEmitida> query = em.createQuery("SELECT e FROM SenhaEmitida e", SenhaEmitida.class);
        return query.getResultList();
	}

	@Override
	public void save(SenhaEmitida obj) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(obj);
            tx.commit(); 
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
	}

	@Override
	public void update(SenhaEmitida obj) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(obj);
            tx.commit(); 
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
	}

	@Override
	public void delete(SenhaEmitida obj) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(obj);
            tx.commit(); 
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
	}


}
