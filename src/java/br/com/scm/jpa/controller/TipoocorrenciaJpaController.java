/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.scm.jpa.controller;

import br.com.scm.entity.Tipoocorrencia;
import br.com.scm.jpa.controller.exceptions.NonexistentEntityException;
import br.com.scm.jpa.controller.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author prohgy
 */
public class TipoocorrenciaJpaController implements Serializable {

    public TipoocorrenciaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipoocorrencia tipoocorrencia) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(tipoocorrencia);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipoocorrencia tipoocorrencia) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            tipoocorrencia = em.merge(tipoocorrencia);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoocorrencia.getIdTipoOcorrencia();
                if (findTipoocorrencia(id) == null) {
                    throw new NonexistentEntityException("The tipoocorrencia with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Tipoocorrencia tipoocorrencia;
            try {
                tipoocorrencia = em.getReference(Tipoocorrencia.class, id);
                tipoocorrencia.getIdTipoOcorrencia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoocorrencia with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipoocorrencia);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tipoocorrencia> findTipoocorrenciaEntities() {
        return findTipoocorrenciaEntities(true, -1, -1);
    }

    public List<Tipoocorrencia> findTipoocorrenciaEntities(int maxResults, int firstResult) {
        return findTipoocorrenciaEntities(false, maxResults, firstResult);
    }

    private List<Tipoocorrencia> findTipoocorrenciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipoocorrencia.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Tipoocorrencia findTipoocorrencia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipoocorrencia.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoocorrenciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tipoocorrencia> rt = cq.from(Tipoocorrencia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
