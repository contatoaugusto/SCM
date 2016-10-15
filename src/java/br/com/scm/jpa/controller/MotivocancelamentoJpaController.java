/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.scm.jpa.controller;

import br.com.scm.entity.Motivocancelamento;
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
public class MotivocancelamentoJpaController implements Serializable {

    public MotivocancelamentoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Motivocancelamento motivocancelamento) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(motivocancelamento);
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

    public void edit(Motivocancelamento motivocancelamento) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            motivocancelamento = em.merge(motivocancelamento);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = motivocancelamento.getIdMotivoCancelamento();
                if (findMotivocancelamento(id) == null) {
                    throw new NonexistentEntityException("The motivocancelamento with id " + id + " no longer exists.");
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
            Motivocancelamento motivocancelamento;
            try {
                motivocancelamento = em.getReference(Motivocancelamento.class, id);
                motivocancelamento.getIdMotivoCancelamento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The motivocancelamento with id " + id + " no longer exists.", enfe);
            }
            em.remove(motivocancelamento);
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

    public List<Motivocancelamento> findMotivocancelamentoEntities() {
        return findMotivocancelamentoEntities(true, -1, -1);
    }

    public List<Motivocancelamento> findMotivocancelamentoEntities(int maxResults, int firstResult) {
        return findMotivocancelamentoEntities(false, maxResults, firstResult);
    }

    private List<Motivocancelamento> findMotivocancelamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Motivocancelamento.class));
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

    public Motivocancelamento findMotivocancelamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Motivocancelamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getMotivocancelamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Motivocancelamento> rt = cq.from(Motivocancelamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
