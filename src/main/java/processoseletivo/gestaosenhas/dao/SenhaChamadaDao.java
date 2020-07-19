package processoseletivo.gestaosenhas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import processoseletivo.gestaosenhas.model.SenhaChamada;

@Repository
public class SenhaChamadaDao implements Dao<SenhaChamada> {

	@PersistenceContext
	private EntityManager em;

	@Override
	public SenhaChamada get(long id) {
		return em.find(SenhaChamada.class, id);
	}

	@Override
	public List<SenhaChamada> getAll() {
        TypedQuery<SenhaChamada> query = em.createQuery("SELECT e FROM SenhaChamada e", SenhaChamada.class);
        return query.getResultList();
	}

	public SenhaChamada getUltimaSenha() {
        TypedQuery<SenhaChamada> query = em.createQuery("SELECT e FROM SenhaChamada e where e.", SenhaChamada.class);
        return query.getSingleResult();
	}
	
	@Override
	public void save(SenhaChamada obj) {
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
	public void update(SenhaChamada obj) {
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
	public void delete(SenhaChamada obj) {
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
