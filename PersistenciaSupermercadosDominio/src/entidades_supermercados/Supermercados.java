
package entidades_supermercados;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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


@Entity
@Table(name = "supermercados")
public class Supermercados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_supermercados", nullable = false)
    private Integer idSupermercados;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "direccion", nullable = false, length = 500)
    private String direccion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supermercadoId")
    @JsonManagedReference
    private List<Comentarios> comentariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supermercadoId")
    @JsonManagedReference
    private List<Productos> productosList;

    public Supermercados() {
    }

    public Supermercados(Integer idSupermercados) {
        this.idSupermercados = idSupermercados;
    }

    public Supermercados(Integer idSupermercados, String nombre, String direccion) {
        this.idSupermercados = idSupermercados;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Integer getIdSupermercados() {
        return idSupermercados;
    }

    public void setIdSupermercados(Integer idSupermercados) {
        this.idSupermercados = idSupermercados;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Comentarios> getComentariosList() {
        return comentariosList;
    }

    public void setComentariosList(List<Comentarios> comentariosList) {
        this.comentariosList = comentariosList;
    }

    public List<Productos> getProductosList() {
        return productosList;
    }

    public void setProductosList(List<Productos> productosList) {
        this.productosList = productosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSupermercados != null ? idSupermercados.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Supermercados)) {
            return false;
        }
        Supermercados other = (Supermercados) object;
        if ((this.idSupermercados == null && other.idSupermercados != null) || (this.idSupermercados != null && !this.idSupermercados.equals(other.idSupermercados))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_supermercados.Supermercados[ idSupermercados=" + idSupermercados + " ]";
    }

}
