/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.scm.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author prohgy
 */
@Entity
@Table(name = "tipoequipamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipoequipamento.findAll", query = "SELECT t FROM Tipoequipamento t"),
    @NamedQuery(name = "Tipoequipamento.findByIdTipoEquipamento", query = "SELECT t FROM Tipoequipamento t WHERE t.idTipoEquipamento = :idTipoEquipamento"),
    @NamedQuery(name = "Tipoequipamento.findByTxTipoEquipamento", query = "SELECT t FROM Tipoequipamento t WHERE t.txTipoEquipamento = :txTipoEquipamento"),
    @NamedQuery(name = "Tipoequipamento.findByIdAtivoTipoEquipamento", query = "SELECT t FROM Tipoequipamento t WHERE t.idAtivoTipoEquipamento = :idAtivoTipoEquipamento"),
    @NamedQuery(name = "Tipoequipamento.findByIdExcluidoTipoEquipamento", query = "SELECT t FROM Tipoequipamento t WHERE t.idExcluidoTipoEquipamento = :idExcluidoTipoEquipamento")})
public class Tipoequipamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipoEquipamento")
    private Integer idTipoEquipamento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "txTipoEquipamento")
    private String txTipoEquipamento;
    @Size(max = 2)
    @Column(name = "idAtivoTipoEquipamento")
    private String idAtivoTipoEquipamento;
    @Size(max = 2)
    @Column(name = "idExcluidoTipoEquipamento")
    private String idExcluidoTipoEquipamento;

    public Tipoequipamento() {
    }

    public Tipoequipamento(Integer idTipoEquipamento) {
        this.idTipoEquipamento = idTipoEquipamento;
    }

    public Tipoequipamento(Integer idTipoEquipamento, String txTipoEquipamento) {
        this.idTipoEquipamento = idTipoEquipamento;
        this.txTipoEquipamento = txTipoEquipamento;
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

    public String getIdAtivoTipoEquipamento() {
        return idAtivoTipoEquipamento;
    }

    public void setIdAtivoTipoEquipamento(String idAtivoTipoEquipamento) {
        this.idAtivoTipoEquipamento = idAtivoTipoEquipamento;
    }

    public String getIdExcluidoTipoEquipamento() {
        return idExcluidoTipoEquipamento;
    }

    public void setIdExcluidoTipoEquipamento(String idExcluidoTipoEquipamento) {
        this.idExcluidoTipoEquipamento = idExcluidoTipoEquipamento;
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
