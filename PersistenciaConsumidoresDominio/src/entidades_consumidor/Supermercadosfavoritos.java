/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades_consumidor;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author josej
 */
@Entity
@Table(name = "supermercadosfavoritos")
public class Supermercadosfavoritos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_favoritos", nullable = false)
    private Integer idFavoritos;
    @Basic(optional = false)
    @Column(name = "supermercado_id", nullable = false)
    private int supermercadoId;
    @JoinColumn(name = "consumidor_id", referencedColumnName = "id_consumidores", nullable = false)
    @ManyToOne(optional = false)
    private Consumidores consumidorId;
  
    public Supermercadosfavoritos() {
    }

    public Supermercadosfavoritos(Integer idFavoritos) {
        this.idFavoritos = idFavoritos;
    }

    public Supermercadosfavoritos(Integer idFavoritos, int supermercadoId) {
        this.idFavoritos = idFavoritos;
        this.supermercadoId = supermercadoId;
    }

    public Integer getIdFavoritos() {
        return idFavoritos;
    }

    public void setIdFavoritos(Integer idFavoritos) {
        this.idFavoritos = idFavoritos;
    }

    public int getSupermercadoId() {
        return supermercadoId;
    }

    public void setSupermercadoId(int supermercadoId) {
        this.supermercadoId = supermercadoId;
    }

    public Consumidores getConsumidorId() {
        return consumidorId;
    }

    public void setConsumidorId(Consumidores consumidorId) {
        this.consumidorId = consumidorId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFavoritos != null ? idFavoritos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Supermercadosfavoritos)) {
            return false;
        }
        Supermercadosfavoritos other = (Supermercadosfavoritos) object;
        if ((this.idFavoritos == null && other.idFavoritos != null) || (this.idFavoritos != null && !this.idFavoritos.equals(other.idFavoritos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_consumidor.Supermercadosfavoritos[ idFavoritos=" + idFavoritos + " ]";
    }
    
}
