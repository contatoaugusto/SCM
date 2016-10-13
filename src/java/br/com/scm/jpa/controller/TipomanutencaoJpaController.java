/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.scm.jpa.controller;

import br.com.scm.entity.Tipomanutencao;
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
public class TipomanutencaoJpaController implements Serializable {

    public TipomanutencaoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipomanutencao tipomanutencao) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(tipomanutencao);
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

    public void edit(Tipomanutencao tipomanutencao) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            tipomanutencao = em.merge(tipomanutencao);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipomanutencao.getIdTipoManutencao();
                if (findTipomanutencao(id) == null) {
                    throw new NonexistentEntityException("The tipomanutencao with id " + id + " no longer exists.");
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
            Tipomanutencao tipomanutencao;
            try {
                tipomanutencao = em.getReference(Tipomanutencao.class, id);
                tipomanutencao.getIdTipoManutencao();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipomanutencao with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipomanutencao);
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

    public List<Tipomanutencao> findTipomanutencaoEntities() {
        return findTipomanutencaoEntities(true, -1, -1);
    }

    public List<Tipomanutencao> findTipomanutencaoEntities(int maxResults, int firstResult) {
        return findTipomanutencaoEntities(false, maxResults, firstResult);
    }

    private List<Tipomanutencao> findTipomanutencaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipomanutencao.class));
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

    public Tipomanutencao findTipomanutencao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipomanutencao.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipomanutencaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tipomanutencao> rt = cq.from(Tipomanutencao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
