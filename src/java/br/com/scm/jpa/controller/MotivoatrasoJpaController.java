/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.scm.jpa.controller;

import br.com.scm.entity.Motivoatraso;
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
public class MotivoatrasoJpaController implements Serializable {

    public MotivoatrasoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Motivoatraso motivoatraso) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(motivoatraso);
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

    public void edit(Motivoatraso motivoatraso) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            motivoatraso = em.merge(motivoatraso);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = motivoatraso.getIdMotivoAtraso();
                if (findMotivoatraso(id) == null) {
                    throw new NonexistentEntityException("The motivoatraso with id " + id + " no longer exists.");
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
            Motivoatraso motivoatraso;
            try {
                motivoatraso = em.getReference(Motivoatraso.class, id);
                motivoatraso.getIdMotivoAtraso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The motivoatraso with id " + id + " no longer exists.", enfe);
            }
            em.remove(motivoatraso);
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

    public List<Motivoatraso> findMotivoatrasoEntities() {
        return findMotivoatrasoEntities(true, -1, -1);
    }

    public List<Motivoatraso> findMotivoatrasoEntities(int maxResults, int firstResult) {
        return findMotivoatrasoEntities(false, maxResults, firstResult);
    }

    private List<Motivoatraso> findMotivoatrasoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Motivoatraso.class));
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

    public Motivoatraso findMotivoatraso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Motivoatraso.class, id);
        } finally {
            em.close();
        }
    }

    public int getMotivoatrasoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Motivoatraso> rt = cq.from(Motivoatraso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
