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
    @NamedQuery(name = "Tipoocorrencia.findAll", query = "SELECT t FROM Tipoocorrencia t"),
    @NamedQuery(name = "Tipoocorrencia.findByIdTipoOcorrencia", query = "SELECT t FROM Tipoocorrencia t WHERE t.idTipoOcorrencia = :idTipoOcorrencia"),
    @NamedQuery(name = "Tipoocorrencia.findByTxTipoOcorrencia", query = "SELECT t FROM Tipoocorrencia t WHERE t.txTipoOcorrencia = :txTipoOcorrencia"),
    @NamedQuery(name = "Tipoocorrencia.findByIcAtivo", query = "SELECT t FROM Tipoocorrencia t WHERE t.icAtivo = :icAtivo")})
public class Tipoocorrencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idTipoOcorrencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String txTipoOcorrencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    private String icAtivo;

    public Tipoocorrencia() {
    }

    public Tipoocorrencia(Integer idTipoOcorrencia) {
        this.idTipoOcorrencia = idTipoOcorrencia;
    }

    public Tipoocorrencia(Integer idTipoOcorrencia, String txTipoOcorrencia, String icAtivo) {
        this.idTipoOcorrencia = idTipoOcorrencia;
        this.txTipoOcorrencia = txTipoOcorrencia;
        this.icAtivo = icAtivo;
    }

    public Integer getIdTipoOcorrencia() {
        return idTipoOcorrencia;
    }

    public void setIdTipoOcorrencia(Integer idTipoOcorrencia) {
        this.idTipoOcorrencia = idTipoOcorrencia;
    }

    public String getTxTipoOcorrencia() {
        return txTipoOcorrencia;
    }

    public void setTxTipoOcorrencia(String txTipoOcorrencia) {
        this.txTipoOcorrencia = txTipoOcorrencia;
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
        hash += (idTipoOcorrencia != null ? idTipoOcorrencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoocorrencia)) {
            return false;
        }
        Tipoocorrencia other = (Tipoocorrencia) object;
        if ((this.idTipoOcorrencia == null && other.idTipoOcorrencia != null) || (this.idTipoOcorrencia != null && !this.idTipoOcorrencia.equals(other.idTipoOcorrencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.scm.entity.Tipoocorrencia[ idTipoOcorrencia=" + idTipoOcorrencia + " ]";
    }
    
}
