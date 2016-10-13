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
    @NamedQuery(name = "Tipoequipamento.findAll", query = "SELECT t FROM Tipoequipamento t"),
    @NamedQuery(name = "Tipoequipamento.findByIdTipoEquipamento", query = "SELECT t FROM Tipoequipamento t WHERE t.idTipoEquipamento = :idTipoEquipamento"),
    @NamedQuery(name = "Tipoequipamento.findByTxTipoEquipamento", query = "SELECT t FROM Tipoequipamento t WHERE t.txTipoEquipamento = :txTipoEquipamento"),
    @NamedQuery(name = "Tipoequipamento.findByIcAtivo", query = "SELECT t FROM Tipoequipamento t WHERE t.icAtivo = :icAtivo")})
public class Tipoequipamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idTipoEquipamento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String txTipoEquipamento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    private String icAtivo;

    public Tipoequipamento() {
    }

    public Tipoequipamento(Integer idTipoEquipamento) {
        this.idTipoEquipamento = idTipoEquipamento;
    }

    public Tipoequipamento(Integer idTipoEquipamento, String txTipoEquipamento, String icAtivo) {
        this.idTipoEquipamento = idTipoEquipamento;
        this.txTipoEquipamento = txTipoEquipamento;
        this.icAtivo = icAtivo;
    }

    public Integer getIdTipoEquipamento() {
        return idTipoEquipamento;
    }

    public void setIdTipoEquipamento(Integer idTipoEquipamento) {
        this.idTipoEquipamento = idTipoEquipamento;
    }

    public String getTxTipoEquipamento() {
        return txTipoEquipamento;
    }

    public void setTxTipoEquipamento(String txTipoEquipamento) {
        this.txTipoEquipamento = txTipoEquipamento;
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
        hash += (idTipoEquipamento != null ? idTipoEquipamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoequipamento)) {
            return false;
        }
        Tipoequipamento other = (Tipoequipamento) object;
        if ((this.idTipoEquipamento == null && other.idTipoEquipamento != null) || (this.idTipoEquipamento != null && !this.idTipoEquipamento.equals(other.idTipoEquipamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.scm.entity.Tipoequipamento[ idTipoEquipamento=" + idTipoEquipamento + " ]";
    }
    
}
