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
    @NamedQuery(name = "Motivoatraso.findAll", query = "SELECT m FROM Motivoatraso m"),
    @NamedQuery(name = "Motivoatraso.findByIdMotivoAtraso", query = "SELECT m FROM Motivoatraso m WHERE m.idMotivoAtraso = :idMotivoAtraso"),
    @NamedQuery(name = "Motivoatraso.findByTxMotivoAtraso", query = "SELECT m FROM Motivoatraso m WHERE m.txMotivoAtraso = :txMotivoAtraso"),
    @NamedQuery(name = "Motivoatraso.findByIcAtivo", query = "SELECT m FROM Motivoatraso m WHERE m.icAtivo = :icAtivo")})
public class Motivoatraso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idMotivoAtraso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String txMotivoAtraso;
    @Size(max = 2)
    private String icAtivo;

    public Motivoatraso() {
    }

    public Motivoatraso(Integer idMotivoAtraso) {
        this.idMotivoAtraso = idMotivoAtraso;
    }

    public Motivoatraso(Integer idMotivoAtraso, String txMotivoAtraso) {
        this.idMotivoAtraso = idMotivoAtraso;
        this.txMotivoAtraso = txMotivoAtraso;
    }

    public Integer getIdMotivoAtraso() {
        return idMotivoAtraso;
    }

    public void setIdMotivoAtraso(Integer idMotivoAtraso) {
        this.idMotivoAtraso = idMotivoAtraso;
    }

    public String getTxMotivoAtraso() {
        return txMotivoAtraso;
    }

    public void setTxMotivoAtraso(String txMotivoAtraso) {
        this.txMotivoAtraso = txMotivoAtraso;
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
        hash += (idMotivoAtraso != null ? idMotivoAtraso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Motivoatraso)) {
            return false;
        }
        Motivoatraso other = (Motivoatraso) object;
        if ((this.idMotivoAtraso == null && other.idMotivoAtraso != null) || (this.idMotivoAtraso != null && !this.idMotivoAtraso.equals(other.idMotivoAtraso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.scm.entity.Motivoatraso[ idMotivoAtraso=" + idMotivoAtraso + " ]";
    }
    
}
