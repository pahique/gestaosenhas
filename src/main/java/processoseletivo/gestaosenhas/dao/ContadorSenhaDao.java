package processoseletivo.gestaosenhas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import processoseletivo.gestaosenhas.model.ContadorSenha;

@Repository
public class ContadorSenhaDao implements Dao<ContadorSenha> {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public ContadorSenha get(long id) {
		return em.find(ContadorSenha.class, id);
	}
	
	public ContadorSenha findByTipoSenha(int tipoSenha) {
        TypedQuery<ContadorSenha> query = em.createQuery("SELECT e FROM ContadorSenha e where e.tipoSenha.codigo = :codigo", ContadorSenha.class);
        query.setParameter("codigo", tipoSenha);
        return query.getSingleResult();
	}

	@Override
	public List<ContadorSenha> getAll() {
        TypedQuery<ContadorSenha> query = em.createQuery("SELECT e FROM ContadorSenha e", ContadorSenha.class);
        return query.getResultList();
	}

	@Override
	public void save(ContadorSenha obj) {
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
	public void update(ContadorSenha obj) {
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
	public void delete(ContadorSenha obj) {
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
