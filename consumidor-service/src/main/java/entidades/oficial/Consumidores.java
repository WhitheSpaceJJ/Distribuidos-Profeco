/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades.oficial;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author josej
 */
@Entity
@Table(name = "consumidores")
public class Consumidores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_consumidores", nullable = false)
    private Integer idConsumidores;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "contrase\u00f1a", nullable = false, length = 500)
    private String contraseña;
    @Basic(optional = false)
    @Column(name = "email", nullable = false, length = 150)
    private String email;
    @Basic(optional = false)
    @Column(name = "edad", nullable = false)
    private int edad;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "consumidorId")
   // @JsonBackReference
    private List<Wishlist> wishlistList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "consumidorId")
//    @JsonBackReference
    private List<Supermercadosfavoritos> supermercadosfavoritosList;

    public Consumidores() {
    }

    public Consumidores(Integer idConsumidores) {
        this.idConsumidores = idConsumidores;
    }

    public Consumidores(Integer idConsumidores, String nombre, String contraseña, String email, int edad) {
        this.idConsumidores = idConsumidores;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.email = email;
        this.edad = edad;
    }

    public Integer getIdConsumidores() {
        return idConsumidores;
    }

    public void setIdConsumidores(Integer idConsumidores) {
        this.idConsumidores = idConsumidores;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public List<Wishlist> getWishlistList() {
        return wishlistList;
    }

    public void setWishlistList(List<Wishlist> wishlistList) {
        this.wishlistList = wishlistList;
    }

    public List<Supermercadosfavoritos> getSupermercadosfavoritosList() {
        return supermercadosfavoritosList;
    }

    public void setSupermercadosfavoritosList(List<Supermercadosfavoritos> supermercadosfavoritosList) {
        this.supermercadosfavoritosList = supermercadosfavoritosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConsumidores != null ? idConsumidores.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consumidores)) {
            return false;
        }
        Consumidores other = (Consumidores) object;
        if ((this.idConsumidores == null && other.idConsumidores != null) || (this.idConsumidores != null && !this.idConsumidores.equals(other.idConsumidores))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Consumidores{" + "idConsumidores=" + idConsumidores + ", nombre=" + nombre + ", contrase\u00f1a=" + contraseña + ", email=" + email + ", edad=" + edad + ", wishlistList=" + wishlistList + ", supermercadosfavoritosList=" + supermercadosfavoritosList + '}';
    }

}
