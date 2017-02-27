package com.unicauca.gymadmdoc.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author CristianCamilo
 */
@Entity
@Table(name = "mv_valoracion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MvValoracion.findAll", query = "SELECT m FROM MvValoracion m"),
    @NamedQuery(name = "MvValoracion.findByValId", query = "SELECT m FROM MvValoracion m WHERE m.valId = :valId"),
    @NamedQuery(name = "MvValoracion.findByValCliente", query = "SELECT m FROM MvValoracion m WHERE m.valCliente = :idCliente ORDER BY m.valFechavaloracion DESC"),
    @NamedQuery(name = "MvValoracion.findIdFechaComparar", query = "SELECT m.resId FROM MvValoracion m WHERE m.valCliente = :idCliente AND m.valFechavaloracion = :fechaValorar ORDER BY m.valFechavaloracion DESC")
    })
public class MvValoracion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "VAL_ID")
    private Integer valId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VAL_ABDOMENINFERIOR")
    private Double valAbdomeninferior;
    @Column(name = "VAL_ABDOMINAL")
    private Double valAbdominal;
    @Column(name = "VAL_BIEPICONDILARFEMORAL")
    private Double valBiepicondilarfemoral;
    @Column(name = "VAL_BIEPICONDILARHUMERAL")
    private Double valBiepicondilarhumeral;
    @Column(name = "VAL_BICEPSCONTRAIDOIZQ")
    private Double valBicepscontraidoizq;
    @Column(name = "VAL_BICEPSRELAJADOIZQ")
    private Double valBicepsrelajadoizq;
    @Column(name = "VAL_CADERA")
    private Double valCadera;
    @Column(name = "VAL_FRECUENCIACARDIACAREPOSO")
    private Double valFrecuenciacardiacareposo;
    @Column(name = "VAL_MEDIALPIERNA")
    private Double valMedialpierna;
    @Column(name = "VAL_MUSLOANTERIOR")
    private Double valMusloanterior;
    @Column(name = "VAL_MUSLOSUPERIORIZQ")
    private Double valMuslosuperiorizq;
    @Column(name = "VAL_PANTORRILLAIZQ")
    private Double valPantorrillaizq;
    @Column(name = "VAL_PECTORAL")
    private Double valPectoral;
    @Column(name = "VAL_PORCENTAJEAGUACORPORAL")
    private Double valPorcentajeaguacorporal;
    @Column(name = "VAL_PORCENTAJEGRASAIMPEDANCIOMETRIA")
    private Double valPorcentajegrasaimpedanciometria;
    @Column(name = "VAL_RADIOCUBITAL")
    private Double valRadiocubital;
    @Column(name = "VAL_SUBESCAPULAR")
    private Double valSubescapular;
    @Column(name = "VAL_SUPRAIIATICO")
    private Double valSupraiiatico;
    @Column(name = "VAL_TORAX")
    private Double valTorax;
    @Column(name = "VAL_TRICEPTS")
    private Double valTricepts;
    @Column(name = "VAL_VO2MAX")
    private Double valVo2max;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VAL_CLIENTE")
    private long valCliente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VAL_VALORADOR")
    private long valValorador;
    @Column(name = "VAL_FECHAVALORACION")
    @Temporal(TemporalType.DATE)
    private Date valFechavaloracion;
    @Column(name = "VAL_EDADDECIMAL")
    private Double valEdaddecimal;
    @Column(name = "VAL_BICEPSRELAJADODER")
    private Double valBicepsrelajadoder;
    @Column(name = "VAL_BICEPSCONTRAIDODER")
    private Double valBicepscontraidoder;
    @Column(name = "VAL_MUSLOSUPERIORDER")
    private Double valMuslosuperiorder;
    @Column(name = "VAL_PANTORRILLADER")
    private Double valPantorrillader;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "valId")
    private List<MvResultado> mvResultadoList;
    @JoinColumn(name = "RES_ID", referencedColumnName = "RES_ID")
    @ManyToOne
    private MvResultado resId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "valId")
    private List<MvRegistrofotografico> mvRegistrofotograficoList;

    public MvValoracion() {
    }

    public MvValoracion(Integer valId) {
        this.valId = valId;
    }

    public MvValoracion(Integer valId, long valCliente, long valValorador) {
        this.valId = valId;
        this.valCliente = valCliente;
        this.valValorador = valValorador;
    }

    public Integer getValId() {
        return valId;
    }

    public void setValId(Integer valId) {
        this.valId = valId;
    }

    public Double getValAbdomeninferior() {
        return valAbdomeninferior;
    }

    public void setValAbdomeninferior(Double valAbdomeninferior) {
        this.valAbdomeninferior = valAbdomeninferior;
    }

    public Double getValAbdominal() {
        return valAbdominal;
    }

    public void setValAbdominal(Double valAbdominal) {
        this.valAbdominal = valAbdominal;
    }

    public Double getValBiepicondilarfemoral() {
        return valBiepicondilarfemoral;
    }

    public void setValBiepicondilarfemoral(Double valBiepicondilarfemoral) {
        this.valBiepicondilarfemoral = valBiepicondilarfemoral;
    }

    public Double getValBiepicondilarhumeral() {
        return valBiepicondilarhumeral;
    }

    public void setValBiepicondilarhumeral(Double valBiepicondilarhumeral) {
        this.valBiepicondilarhumeral = valBiepicondilarhumeral;
    }

    public Double getValBicepscontraidoizq() {
        return valBicepscontraidoizq;
    }

    public void setValBicepscontraidoizq(Double valBicepscontraidoizq) {
        this.valBicepscontraidoizq = valBicepscontraidoizq;
    }

    public Double getValBicepsrelajadoizq() {
        return valBicepsrelajadoizq;
    }

    public void setValBicepsrelajadoizq(Double valBicepsrelajadoizq) {
        this.valBicepsrelajadoizq = valBicepsrelajadoizq;
    }

    public Double getValCadera() {
        return valCadera;
    }

    public void setValCadera(Double valCadera) {
        this.valCadera = valCadera;
    }

    public Double getValFrecuenciacardiacareposo() {
        return valFrecuenciacardiacareposo;
    }

    public void setValFrecuenciacardiacareposo(Double valFrecuenciacardiacareposo) {
        this.valFrecuenciacardiacareposo = valFrecuenciacardiacareposo;
    }

    public Double getValMedialpierna() {
        return valMedialpierna;
    }

    public void setValMedialpierna(Double valMedialpierna) {
        this.valMedialpierna = valMedialpierna;
    }

    public Double getValMusloanterior() {
        return valMusloanterior;
    }

    public void setValMusloanterior(Double valMusloanterior) {
        this.valMusloanterior = valMusloanterior;
    }

    public Double getValMuslosuperiorizq() {
        return valMuslosuperiorizq;
    }

    public void setValMuslosuperiorizq(Double valMuslosuperiorizq) {
        this.valMuslosuperiorizq = valMuslosuperiorizq;
    }

    public Double getValPantorrillaizq() {
        return valPantorrillaizq;
    }

    public void setValPantorrillaizq(Double valPantorrillaizq) {
        this.valPantorrillaizq = valPantorrillaizq;
    }

    public Double getValPectoral() {
        return valPectoral;
    }

    public void setValPectoral(Double valPectoral) {
        this.valPectoral = valPectoral;
    }

    public Double getValPorcentajeaguacorporal() {
        return valPorcentajeaguacorporal;
    }

    public void setValPorcentajeaguacorporal(Double valPorcentajeaguacorporal) {
        this.valPorcentajeaguacorporal = valPorcentajeaguacorporal;
    }

    public Double getValPorcentajegrasaimpedanciometria() {
        return valPorcentajegrasaimpedanciometria;
    }

    public void setValPorcentajegrasaimpedanciometria(Double valPorcentajegrasaimpedanciometria) {
        this.valPorcentajegrasaimpedanciometria = valPorcentajegrasaimpedanciometria;
    }

    public Double getValRadiocubital() {
        return valRadiocubital;
    }

    public void setValRadiocubital(Double valRadiocubital) {
        this.valRadiocubital = valRadiocubital;
    }

    public Double getValSubescapular() {
        return valSubescapular;
    }

    public void setValSubescapular(Double valSubescapular) {
        this.valSubescapular = valSubescapular;
    }

    public Double getValSupraiiatico() {
        return valSupraiiatico;
    }

    public void setValSupraiiatico(Double valSupraiiatico) {
        this.valSupraiiatico = valSupraiiatico;
    }

    public Double getValTorax() {
        return valTorax;
    }

    public void setValTorax(Double valTorax) {
        this.valTorax = valTorax;
    }

    public Double getValTricepts() {
        return valTricepts;
    }

    public void setValTricepts(Double valTricepts) {
        this.valTricepts = valTricepts;
    }

    public Double getValVo2max() {
        return valVo2max;
    }

    public void setValVo2max(Double valVo2max) {
        this.valVo2max = valVo2max;
    }

    public long getValCliente() {
        return valCliente;
    }

    public void setValCliente(long valCliente) {
        this.valCliente = valCliente;
    }

    public long getValValorador() {
        return valValorador;
    }

    public void setValValorador(long valValorador) {
        this.valValorador = valValorador;
    }

    public Date getValFechavaloracion() {
        return valFechavaloracion;
    }

    public void setValFechavaloracion(Date valFechavaloracion) {
        this.valFechavaloracion = valFechavaloracion;
    }

    public Double getValEdaddecimal() {
        return valEdaddecimal;
    }

    public void setValEdaddecimal(Double valEdaddecimal) {
        this.valEdaddecimal = valEdaddecimal;
    }

    public Double getValBicepsrelajadoder() {
        return valBicepsrelajadoder;
    }

    public void setValBicepsrelajadoder(Double valBicepsrelajadoder) {
        this.valBicepsrelajadoder = valBicepsrelajadoder;
    }

    public Double getValBicepscontraidoder() {
        return valBicepscontraidoder;
    }

    public void setValBicepscontraidoder(Double valBicepscontraidoder) {
        this.valBicepscontraidoder = valBicepscontraidoder;
    }

    public Double getValMuslosuperiorder() {
        return valMuslosuperiorder;
    }

    public void setValMuslosuperiorder(Double valMuslosuperiorder) {
        this.valMuslosuperiorder = valMuslosuperiorder;
    }

    public Double getValPantorrillader() {
        return valPantorrillader;
    }

    public void setValPantorrillader(Double valPantorrillader) {
        this.valPantorrillader = valPantorrillader;
    }

    @XmlTransient
    public List<MvResultado> getMvResultadoList() {
        return mvResultadoList;
    }

    public void setMvResultadoList(List<MvResultado> mvResultadoList) {
        this.mvResultadoList = mvResultadoList;
    }

    public MvResultado getResId() {
        return resId;
    }

    public void setResId(MvResultado resId) {
        this.resId = resId;
    }

    @XmlTransient
    public List<MvRegistrofotografico> getMvRegistrofotograficoList() {
        return mvRegistrofotograficoList;
    }

    public void setMvRegistrofotograficoList(List<MvRegistrofotografico> mvRegistrofotograficoList) {
        this.mvRegistrofotograficoList = mvRegistrofotograficoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (valId != null ? valId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MvValoracion)) {
            return false;
        }
        MvValoracion other = (MvValoracion) object;
        if ((this.valId == null && other.valId != null) || (this.valId != null && !this.valId.equals(other.valId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Valoracion[" + valId + "]";
    }
    
}
