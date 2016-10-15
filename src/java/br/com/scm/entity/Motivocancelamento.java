/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.scm.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author prohgy
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Motivocancelamento.findAll", query = "SELECT m FROM Motivocancelamento m"),
    @NamedQuery(name = "Motivocancelamento.findByIdMotivoCancelamento", query = "SELECT m FROM Motivocancelamento m WHERE m.idMotivoCancelamento = :idMotivoCancelamento"),
    @NamedQuery(name = "Motivocancelamento.findByTxMotivoCancelamento", query = "SELECT m FROM Motivocancelamento m WHERE m.txMotivoCancelamento = :txMotivoCancelamento"),
    @NamedQuery(name = "Motivocancelamento.findByIcAtivo", query = "SELECT m FROM Motivocancelamento m WHERE m.icAtivo = :icAtivo")})
public class Motivocancelamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idMotivoCancelamento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String txMotivoCancelamento;
    @Size(max = 2)
    private String icAtivo;

    public Motivocancelamento() {
    }

    public Motivocancelamento(Integer idMotivoCancelamento) {
        this.idMotivoCancelamento = idMotivoCancelamento;
    }

    public Motivocancelamento(Integer idMotivoCancelamento, String txMotivoCancelamento) {
        this.idMotivoCancelamento = idMotivoCancelamento;
        this.txMotivoCancelamento = txMotivoCancelamento;
    }

    public Integer getIdMotivoCancelamento() {
        return idMotivoCancelamento;
    }

    public void setIdMotivoCancelamento(Integer idMotivoCancelamento) {
        this.idMotivoCancelamento = idMotivoCancelamento;
    }

    public String getTxMotivoCancelamento() {
        return txMotivoCancelamento;
    }

    public void setTxMotivoCancelamento(String txMotivoCancelamento) {
        this.txMotivoCancelamento = txMotivoCancelamento;
    }

    public String getIcAtivo() {
        return icAtivo;
    }

    public void setIcAtivo(String icAtivo) {
        this.icAtivo = icAtivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMotivoCancelamento != null ? idMotivoCancelamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Motivocancelamento)) {
            return false;
        }
        Motivocancelamento other = (Motivocancelamento) object;
        if ((this.idMotivoCancelamento == null && other.idMotivoCancelamento != null) || (this.idMotivoCancelamento != null && !this.idMotivoCancelamento.equals(other.idMotivoCancelamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.scm.entity.Motivocancelamento[ idMotivoCancelamento=" + idMotivoCancelamento + " ]";
    }
    
}
