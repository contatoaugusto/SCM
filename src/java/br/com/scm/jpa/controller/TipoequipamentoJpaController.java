/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.scm.jpa.controller;

import br.com.scm.jpa.controller.exceptions.NonexistentEntityException;
import br.com.scm.jpa.controller.exceptions.RollbackFailureException;
import br.com.scm.entity.Tipoequipamento;
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
public class TipoequipamentoJpaController implements Serializable {

    public TipoequipamentoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipoequipamento tipoequipamento) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(tipoequipamento);
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

    public void edit(Tipoequipamento tipoequipamento) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            tipoequipamento = em.merge(tipoequipamento);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoequipamento.getIdTipoEquipamento();
                if (findTipoequipamento(id) == null) {
                    throw new NonexistentEntityException("The tipoequipamento with id " + id + " no longer exists.");
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
            Tipoequipamento tipoequipamento;
            try {
                tipoequipamento = em.getReference(Tipoequipamento.class, id);
                tipoequipamento.getIdTipoEquipamento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoequipamento with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipoequipamento);
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

    public List<Tipoequipamento> findTipoequipamentoEntities() {
        return findTipoequipamentoEntities(true, -1, -1);
    }

    public List<Tipoequipamento> findTipoequipamentoEntities(int maxResults, int firstResult) {
        return findTipoequipamentoEntities(false, maxResults, firstResult);
    }

    private List<Tipoequipamento> findTipoequipamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipoequipamento.class));
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

    public Tipoequipamento findTipoequipamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipoequipamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoequipamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tipoequipamento> rt = cq.from(Tipoequipamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
