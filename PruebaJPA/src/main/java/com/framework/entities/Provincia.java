/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mariano
 */
@Entity
@Table(name = "provincia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Provincia.findAll", query = "SELECT p FROM Provincia p"),
    @NamedQuery(name = "Provincia.findById", query = "SELECT p FROM Provincia p WHERE p.id = :id"),
    @NamedQuery(name = "Provincia.findByNombre", query = "SELECT p FROM Provincia p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Provincia.findBySuperficie", query = "SELECT p FROM Provincia p WHERE p.superficie = :superficie"),
    @NamedQuery(name = "Provincia.findByNumMunicipios", query = "SELECT p FROM Provincia p WHERE p.numMunicipios = :numMunicipios"),
    @NamedQuery(name = "Provincia.findByCapital", query = "SELECT p FROM Provincia p WHERE p.capital = :capital"),
    @NamedQuery(name = "Provincia.findByCa", query = "SELECT p FROM Provincia p WHERE p.ca = :ca")})
public class Provincia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "superficie")
    private BigDecimal superficie;
    @Basic(optional = false)
    @Column(name = "num_municipios")
    private int numMunicipios;
    @Basic(optional = false)
    @Column(name = "capital")
    private String capital;
    @Basic(optional = false)
    @Column(name = "ca")
    private int ca;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provincia", fetch = FetchType.LAZY)
    private List<Municipio> municipioList;

    public Provincia() {
    }

    public Provincia(String id) {
        this.id = id;
    }

    public Provincia(String id, String nombre, int numMunicipios, String capital, int ca) {
        this.id = id;
        this.nombre = nombre;
        this.numMunicipios = numMunicipios;
        this.capital = capital;
        this.ca = ca;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getSuperficie() {
        return superficie;
    }

    public void setSuperficie(BigDecimal superficie) {
        this.superficie = superficie;
    }

    public int getNumMunicipios() {
        return numMunicipios;
    }

    public void setNumMunicipios(int numMunicipios) {
        this.numMunicipios = numMunicipios;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public int getCa() {
        return ca;
    }

    public void setCa(int ca) {
        this.ca = ca;
    }

    @XmlTransient
    public List<Municipio> getMunicipioList() {
        return municipioList;
    }

    public void setMunicipioList(List<Municipio> municipioList) {
        this.municipioList = municipioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Provincia)) {
            return false;
        }
        Provincia other = (Provincia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.framework.entities.Provincia[ id=" + id + " ]";
    }
    
}
