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
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdLogonUsuario", query = "SELECT u FROM Usuario u WHERE u.idLogonUsuario = :idLogonUsuario"),
    @NamedQuery(name = "Usuario.findByIdMatriculaUsuario", query = "SELECT u FROM Usuario u WHERE u.idMatriculaUsuario = :idMatriculaUsuario"),
    @NamedQuery(name = "Usuario.findByNmFuncionarioUsuario", query = "SELECT u FROM Usuario u WHERE u.nmFuncionarioUsuario = :nmFuncionarioUsuario"),
    @NamedQuery(name = "Usuario.findByIdPerfilUsuario", query = "SELECT u FROM Usuario u WHERE u.idPerfilUsuario = :idPerfilUsuario"),
    @NamedQuery(name = "Usuario.findByIdSenhaUsuario", query = "SELECT u FROM Usuario u WHERE u.idSenhaUsuario = :idSenhaUsuario")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "idLogonUsuario")
    private String idLogonUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idMatriculaUsuario")
    private int idMatriculaUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nmFuncionarioUsuario")
    private String nmFuncionarioUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPerfilUsuario")
    private int idPerfilUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "idSenhaUsuario")
    private String idSenhaUsuario;

    public Usuario() {
    }

    public Usuario(String idLogonUsuario) {
        this.idLogonUsuario = idLogonUsuario;
    }

    public Usuario(String idLogonUsuario, int idMatriculaUsuario, String nmFuncionarioUsuario, int idPerfilUsuario, String idSenhaUsuario) {
        this.idLogonUsuario = idLogonUsuario;
        this.idMatriculaUsuario = idMatriculaUsuario;
        this.nmFuncionarioUsuario = nmFuncionarioUsuario;
        this.idPerfilUsuario = idPerfilUsuario;
        this.idSenhaUsuario = idSenhaUsuario;
    }

    public String getIdLogonUsuario() {
        return idLogonUsuario;
    }

    public void setIdLogonUsuario(String idLogonUsuario) {
        this.idLogonUsuario = idLogonUsuario;
    }

    public int getIdMatriculaUsuario() {
        return idMatriculaUsuario;
    }

    public void setIdMatriculaUsuario(int idMatriculaUsuario) {
        this.idMatriculaUsuario = idMatriculaUsuario;
    }

    public String getNmFuncionarioUsuario() {
        return nmFuncionarioUsuario;
    }

    public void setNmFuncionarioUsuario(String nmFuncionarioUsuario) {
        this.nmFuncionarioUsuario = nmFuncionarioUsuario;
    }

    public int getIdPerfilUsuario() {
        return idPerfilUsuario;
    }

    public void setIdPerfilUsuario(int idPerfilUsuario) {
        this.idPerfilUsuario = idPerfilUsuario;
    }

    public String getIdSenhaUsuario() {
        return idSenhaUsuario;
    }

    public void setIdSenhaUsuario(String idSenhaUsuario) {
        this.idSenhaUsuario = idSenhaUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLogonUsuario != null ? idLogonUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idLogonUsuario == null && other.idLogonUsuario != null) || (this.idLogonUsuario != null && !this.idLogonUsuario.equals(other.idLogonUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.scm.entity.Usuario[ idLogonUsuario=" + idLogonUsuario + " ]";
    }
    
}
