/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades.oficial;

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
@Table(name = "wishlist")
public class Wishlist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_wishlist", nullable = false)
    private Integer idWishlist;
    @Basic(optional = false)
    @Column(name = "deseo", nullable = false, length = 500)
    private String deseo;
    @Basic(optional = false)
    @Column(name = "supermercado_id", nullable = false)
    private int supermercadoId;
    @JoinColumn(name = "consumidor_id", referencedColumnName = "id_consumidores", nullable = false)
    @ManyToOne(optional = false)
    private Consumidores consumidorId;
  
    public Wishlist() {
    }

    public Wishlist(Integer idWishlist) {
        this.idWishlist = idWishlist;
    }

    public Wishlist(Integer idWishlist, String deseo, int supermercadoId) {
        this.idWishlist = idWishlist;
        this.deseo = deseo;
        this.supermercadoId = supermercadoId;
    }

    public Integer getIdWishlist() {
        return idWishlist;
    }

    public void setIdWishlist(Integer idWishlist) {
        this.idWishlist = idWishlist;
    }

    public String getDeseo() {
        return deseo;
    }

    public void setDeseo(String deseo) {
        this.deseo = deseo;
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
        hash += (idWishlist != null ? idWishlist.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wishlist)) {
            return false;
        }
        Wishlist other = (Wishlist) object;
        if ((this.idWishlist == null && other.idWishlist != null) || (this.idWishlist != null && !this.idWishlist.equals(other.idWishlist))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_consumidor.Wishlist[ idWishlist=" + idWishlist + " ]";
    }
    
}
