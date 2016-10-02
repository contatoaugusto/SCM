/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.scm.facade;

import br.com.scm.entity.Tipoequipamento;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author prohgy
 */
@Stateless
public class TipoequipamentoFacade extends AbstractFacade<Tipoequipamento> {

    @PersistenceContext(unitName = "SCMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoequipamentoFacade() {
        super(Tipoequipamento.class);
    }
    
}
