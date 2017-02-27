/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ROED26
 */
@Entity
@Table(name = "mu_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MuUsuario.findAll", query = "SELECT m FROM MuUsuario m"),
    @NamedQuery(name = "MuUsuario.findByUsuIdentificacion", query = "SELECT m FROM MuUsuario m WHERE m.usuIdentificacion = :usuIdentificacion"),
    @NamedQuery(name = "MuUsuario.findByUsuCodigo", query = "SELECT m FROM MuUsuario m WHERE m.usuCodigo = :usuCodigo"),
    @NamedQuery(name = "MuUsuario.findByUsuNombres", query = "SELECT m FROM MuUsuario m WHERE m.usuNombres = :usuNombres"),
    @NamedQuery(name = "MuUsuario.findByUsuApellido1", query = "SELECT m FROM MuUsuario m WHERE m.usuApellido1 = :usuApellido1"),
    @NamedQuery(name = "MuUsuario.findByUsuApellido2", query = "SELECT m FROM MuUsuario m WHERE m.usuApellido2 = :usuApellido2"),
    @NamedQuery(name = "MuUsuario.findByUsuFechaNacimiento", query = "SELECT m FROM MuUsuario m WHERE m.usuFechaNacimiento = :usuFechaNacimiento"),
    @NamedQuery(name = "MuUsuario.findByUsuGenero", query = "SELECT m FROM MuUsuario m WHERE m.usuGenero = :usuGenero"),
    @NamedQuery(name = "MuUsuario.findByUsuTelefono", query = "SELECT m FROM MuUsuario m WHERE m.usuTelefono = :usuTelefono"),
    @NamedQuery(name = "MuUsuario.findByUsuCelular", query = "SELECT m FROM MuUsuario m WHERE m.usuCelular = :usuCelular"),
    @NamedQuery(name = "MuUsuario.findByUsuEmail", query = "SELECT m FROM MuUsuario m WHERE m.usuEmail = :usuEmail"),
    @NamedQuery(name = "MuUsuario.findByUsuContrasena", query = "SELECT m FROM MuUsuario m WHERE m.usuContrasena = :usuContrasena"),
    @NamedQuery(name = "MuUsuario.findByUsuNombreUsuario", query = "SELECT m FROM MuUsuario m WHERE m.usuNombreUsuario = :usuNombreUsuario"),
    @NamedQuery(name = "MuUsuario.findByUsuEstado", query = "SELECT m FROM MuUsuario m WHERE m.usuEstado = :usuEstado"),
    //Agregadas
    //@NamedQuery(name = "MuUsuario.findByNombresApellidosFunciona", query = "SELECT m FROM MuUsuario m WHERE LOWER(CONCAT(CONCAT(CONCAT(CONCAT(m.usuNombres,' '),m.usuApellido1),' '),m.usuApellido2)) LIKE :nombresApellidos "),
    @NamedQuery(name = "MuUsuario.findByNombresApellidos", query = "SELECT m FROM MuUsuario m WHERE LOWER(CONCAT(CONCAT(CONCAT(CONCAT(CONCAT(CONCAT(m.usuIdentificacion,' '),m.usuNombres),' '),m.usuApellido1),' '),m.usuApellido2)) LIKE :nombresApellidos "),
    @NamedQuery(name = "MuUsuario.findByNombresApellidosFuncionarios", query = "SELECT m FROM MuUsuario m WHERE (m.ocuId.ocuDescripcion=:ocupacionD OR m.ocuId.ocuDescripcion=:ocupacionA) AND LOWER(CONCAT(CONCAT(CONCAT(CONCAT(CONCAT(CONCAT(m.usuIdentificacion,' '),m.usuNombres),' '),m.usuApellido1),' '),m.usuApellido2)) LIKE :nombresApellidos"),
    @NamedQuery(name = "MuUsuario.findByNombresApellidosEstudiantes", query = "SELECT m FROM MuUsuario m WHERE LOWER(CONCAT(CONCAT(CONCAT(CONCAT(CONCAT(CONCAT(m.usuIdentificacion,' '),m.usuNombres),' '),m.usuApellido1),' '),m.usuApellido2)) LIKE :nombresApellidos AND m.ocuId.ocuDescripcion =:ocupacionE"),
    @NamedQuery(name = "MuUsuario.findByNombresApellidosFamiliares", query = "SELECT m FROM MuUsuario m WHERE (m.ocuId.ocuDescripcion =:ocupacionHD OR m.ocuId.ocuDescripcion =:ocupacionED OR m.ocuId.ocuDescripcion =:ocupacionEAD OR m.ocuId.ocuDescripcion =:ocupacionAD OR m.ocuId.ocuDescripcion =:ocupacionHA OR m.ocuId.ocuDescripcion =:ocupacionEA OR m.ocuId.ocuDescripcion =:ocupacionEAA OR m.ocuId.ocuDescripcion =:ocupacionAA) AND LOWER(CONCAT(CONCAT(CONCAT(CONCAT(m.usuNombres,' '),m.usuApellido1),' '),m.usuApellido2)) LIKE :nombresApellidos "),
    @NamedQuery(name = "MuUsuario.findByFuncionarios", query = "SELECT m FROM MuUsuario m WHERE m.ocuId.ocuDescripcion =:ocupacionD OR m.ocuId.ocuDescripcion =:ocupacionA"),
    @NamedQuery(name = "MuUsuario.findByEstudiantes", query = "SELECT m FROM MuUsuario m WHERE m.ocuId.ocuDescripcion =:ocupacionE"),
    @NamedQuery(name = "MuUsuario.findByFamiliares", query = "SELECT m FROM MuUsuario m WHERE m.ocuId.ocuDescripcion =:ocupacionHD OR m.ocuId.ocuDescripcion =:ocupacionED OR m.ocuId.ocuDescripcion =:ocupacionEAD OR m.ocuId.ocuDescripcion =:ocupacionAD OR m.ocuId.ocuDescripcion =:ocupacionHA OR m.ocuId.ocuDescripcion =:ocupacionEA OR m.ocuId.ocuDescripcion =:ocupacionEAA OR m.ocuId.ocuDescripcion =:ocupacionAA"),
    
    @NamedQuery(name = "MuUsuario.findByNameFuncionarios", query = "SELECT m FROM MuUsuario m WHERE LOWER(CONCAT(CONCAT(m.usuNombres,' '),m.usuApellido1)) LIKE :nombre AND m.ocuId IS NOT NULL"),
    @NamedQuery(name = "MuUsuario.findByNameEstudiante", query = "SELECT m FROM MuUsuario m WHERE LOWER(CONCAT(CONCAT(m.usuNombres,' '),m.usuApellido1)) LIKE :nombre AND m.ocuId IS NULL AND m.facDepId IS NOT NULL"),
    @NamedQuery(name = "MuUsuario.findByNameFamiliar", query = "SELECT m FROM MuUsuario m WHERE LOWER(CONCAT(CONCAT(m.usuNombres,' '),m.usuApellido1)) LIKE :nombre"),

})
public class MuUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "USU_IDENTIFICACION")
    private Long usuIdentificacion;
    @Size(max = 150)
    @Column(name = "USU_CODIGO")
    private String usuCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "USU_NOMBRES")
    private String usuNombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "USU_APELLIDO1")
    private String usuApellido1;
    @Size(max = 25)
    @Column(name = "USU_APELLIDO2")
    private String usuApellido2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USU_FECHA_NACIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date usuFechaNacimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "USU_GENERO")
    private String usuGenero;
    @Size(max = 15)
    @Column(name = "USU_TELEFONO")
    private String usuTelefono;
    @Size(max = 15)
    @Column(name = "USU_CELULAR")
    private String usuCelular;
    @Size(max = 50)
    @Column(name = "USU_EMAIL")
    private String usuEmail;
    @Lob
    @Column(name = "USU_FOTO")
    private byte[] usuFoto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 350)
    @Column(name = "USU_CONTRASENA")
    private String usuContrasena;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "USU_NOMBRE_USUARIO")
    private String usuNombreUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "USU_ESTADO")
    private String usuEstado;
    @JoinTable(name = "usuario_medicamento", joinColumns = {
        @JoinColumn(name = "USU_IDENTIFICACION", referencedColumnName = "USU_IDENTIFICACION")}, inverseJoinColumns = {
        @JoinColumn(name = "MED_ID", referencedColumnName = "MED_ID")})
    @ManyToMany
    private Collection<MuMedicamento> muMedicamentoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "muUsuario")
    private Collection<MuUsuariogrupo> muUsuariogrupoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuIdentificacion")
    private Collection<MuAntecedenteSalud> muAntecedenteSaludCollection;
    @JoinColumn(name = "FAC_DEP_ID", referencedColumnName = "FAC_DEP_ID")
    @ManyToOne
    private MuFacultadDependencia facDepId;
    @OneToMany(mappedBy = "familiarIdentificacion")
    private Collection<MuUsuario> muUsuarioCollection;
    @JoinColumn(name = "FAMILIAR_IDENTIFICACION", referencedColumnName = "USU_IDENTIFICACION")
    @ManyToOne
    private MuUsuario familiarIdentificacion;
    @JoinColumn(name = "OCU_ID", referencedColumnName = "OCU_ID")
    @ManyToOne(optional = false)
    private MuOcupacion ocuId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuIdentificacion")
    private Collection<MuDiagnosticoMedico> muDiagnosticoMedicoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuIdentificacion")
    private Collection<MuExamenFisico> muExamenFisicoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuIdentificacion")
    private Collection<MuEvaluacion> muEvaluacionCollection;

    public MuUsuario() {
    }

    public MuUsuario(Long usuIdentificacion) {
        this.usuIdentificacion = usuIdentificacion;
    }

    public MuUsuario(Long usuIdentificacion, String usuNombres, String usuApellido1, Date usuFechaNacimiento, String usuGenero, String usuContrasena, String usuNombreUsuario, String usuEstado) {
        this.usuIdentificacion = usuIdentificacion;
        this.usuNombres = usuNombres;
        this.usuApellido1 = usuApellido1;
        this.usuFechaNacimiento = usuFechaNacimiento;
        this.usuGenero = usuGenero;
        this.usuContrasena = usuContrasena;
        this.usuNombreUsuario = usuNombreUsuario;
        this.usuEstado = usuEstado;
    }

    public Long getUsuIdentificacion() {
        return usuIdentificacion;
    }

    public void setUsuIdentificacion(Long usuIdentificacion) {
        this.usuIdentificacion = usuIdentificacion;
    }

    public String getUsuCodigo() {
        return usuCodigo;
    }

    public void setUsuCodigo(String usuCodigo) {
        this.usuCodigo = usuCodigo;
    }

    public String getUsuNombres() {
        return usuNombres;
    }

    public void setUsuNombres(String usuNombres) {
        this.usuNombres = usuNombres;
    }

    public String getUsuApellido1() {
        return usuApellido1;
    }

    public void setUsuApellido1(String usuApellido1) {
        this.usuApellido1 = usuApellido1;
    }

    public String getUsuApellido2() {
        return usuApellido2;
    }

    public void setUsuApellido2(String usuApellido2) {
        this.usuApellido2 = usuApellido2;
    }

    public Date getUsuFechaNacimiento() {
        return usuFechaNacimiento;
    }

    public void setUsuFechaNacimiento(Date usuFechaNacimiento) {
        this.usuFechaNacimiento = usuFechaNacimiento;
    }

    public String getUsuGenero() {
        return usuGenero;
    }

    public void setUsuGenero(String usuGenero) {
        this.usuGenero = usuGenero;
    }

    public String getUsuTelefono() {
        return usuTelefono;
    }

    public void setUsuTelefono(String usuTelefono) {
        this.usuTelefono = usuTelefono;
    }

    public String getUsuCelular() {
        return usuCelular;
    }

    public void setUsuCelular(String usuCelular) {
        this.usuCelular = usuCelular;
    }

    public String getUsuEmail() {
        return usuEmail;
    }

    public void setUsuEmail(String usuEmail) {
        this.usuEmail = usuEmail;
    }

    public byte[] getUsuFoto() {
        return usuFoto;
    }

    public void setUsuFoto(byte[] usuFoto) {
        this.usuFoto = usuFoto;
    }

    public String getUsuContrasena() {
        return usuContrasena;
    }

    public void setUsuContrasena(String usuContrasena) {
        this.usuContrasena = usuContrasena;
    }

    public String getUsuNombreUsuario() {
        return usuNombreUsuario;
    }

    public void setUsuNombreUsuario(String usuNombreUsuario) {
        this.usuNombreUsuario = usuNombreUsuario;
    }

    public String getUsuEstado() {
        return usuEstado;
    }

    public void setUsuEstado(String usuEstado) {
        this.usuEstado = usuEstado;
    }

    @XmlTransient
    public Collection<MuMedicamento> getMuMedicamentoCollection() {
        return muMedicamentoCollection;
    }

    public void setMuMedicamentoCollection(Collection<MuMedicamento> muMedicamentoCollection) {
        this.muMedicamentoCollection = muMedicamentoCollection;
    }

    @XmlTransient
    public Collection<MuUsuariogrupo> getMuUsuariogrupoCollection() {
        return muUsuariogrupoCollection;
    }

    public void setMuUsuariogrupoCollection(Collection<MuUsuariogrupo> muUsuariogrupoCollection) {
        this.muUsuariogrupoCollection = muUsuariogrupoCollection;
    }

    @XmlTransient
    public Collection<MuAntecedenteSalud> getMuAntecedenteSaludCollection() {
        return muAntecedenteSaludCollection;
    }

    public void setMuAntecedenteSaludCollection(Collection<MuAntecedenteSalud> muAntecedenteSaludCollection) {
        this.muAntecedenteSaludCollection = muAntecedenteSaludCollection;
    }

    public MuFacultadDependencia getFacDepId() {
        return facDepId;
    }

    public void setFacDepId(MuFacultadDependencia facDepId) {
        this.facDepId = facDepId;
    }

    @XmlTransient
    public Collection<MuUsuario> getMuUsuarioCollection() {
        return muUsuarioCollection;
    }

    public void setMuUsuarioCollection(Collection<MuUsuario> muUsuarioCollection) {
        this.muUsuarioCollection = muUsuarioCollection;
    }

    public MuUsuario getFamiliarIdentificacion() {
        return familiarIdentificacion;
    }

    public void setFamiliarIdentificacion(MuUsuario familiarIdentificacion) {
        this.familiarIdentificacion = familiarIdentificacion;
    }

    public MuOcupacion getOcuId() {
        return ocuId;
    }

    public void setOcuId(MuOcupacion ocuId) {
        this.ocuId = ocuId;
    }

    @XmlTransient
    public Collection<MuDiagnosticoMedico> getMuDiagnosticoMedicoCollection() {
        return muDiagnosticoMedicoCollection;
    }

    public void setMuDiagnosticoMedicoCollection(Collection<MuDiagnosticoMedico> muDiagnosticoMedicoCollection) {
        this.muDiagnosticoMedicoCollection = muDiagnosticoMedicoCollection;
    }

    @XmlTransient
    public Collection<MuExamenFisico> getMuExamenFisicoCollection() {
        return muExamenFisicoCollection;
    }

    public void setMuExamenFisicoCollection(Collection<MuExamenFisico> muExamenFisicoCollection) {
        this.muExamenFisicoCollection = muExamenFisicoCollection;
    }

    @XmlTransient
    public Collection<MuEvaluacion> getMuEvaluacionCollection() {
        return muEvaluacionCollection;
    }

    public void setMuEvaluacionCollection(Collection<MuEvaluacion> muEvaluacionCollection) {
        this.muEvaluacionCollection = muEvaluacionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuIdentificacion != null ? usuIdentificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuUsuario)) {
            return false;
        }
        MuUsuario other = (MuUsuario) object;
        if ((this.usuIdentificacion == null && other.usuIdentificacion != null) || (this.usuIdentificacion != null && !this.usuIdentificacion.equals(other.usuIdentificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.gymadmdoc.entities.MuUsuario[ usuIdentificacion=" + usuIdentificacion + " ]";
    }

    public int calcularEdad() {
        int edad;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fecha_nac = formato.format(this.usuFechaNacimiento);
        Date fechaActual = new Date();
        String hoy = formato.format(fechaActual);
        String[] dat1 = fecha_nac.split("/");
        String[] dat2 = hoy.split("/");
        edad = Integer.parseInt(dat2[2]) - Integer.parseInt(dat1[2]);
        int mes = Integer.parseInt(dat2[1]) - Integer.parseInt(dat1[1]);
        if (mes < 0) {
            edad = edad - 1;
        } else if (mes == 0) {
            int dia = Integer.parseInt(dat2[0]) - Integer.parseInt(dat1[0]);
            if (dia > 0) {
                edad = edad - 1;
            }
        }
        return edad;
    }
}
