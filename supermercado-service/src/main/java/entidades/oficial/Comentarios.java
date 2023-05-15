
package entidades.oficial;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

@Entity
@Table(name = "comentarios")
public class Comentarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_comentarios", nullable = false)
    private Integer idComentarios;
    @Basic(optional = false)
    @Column(name = "consumidor_id", nullable = false)
    private int consumidorId;
    @Basic(optional = false)
    @Column(name = "mensaje", nullable = false, length = 1000)
    private String mensaje;
    @JoinColumn(name = "supermercado_id", referencedColumnName = "id_supermercados", nullable = false)
    @ManyToOne(optional = false)
       @JsonBackReference
    private Supermercados supermercadoId;

    public Comentarios() {
    }

    public Comentarios(Integer idComentarios) {
        this.idComentarios = idComentarios;
    }

    public Comentarios(Integer idComentarios, int consumidorId, String mensaje) {
        this.idComentarios = idComentarios;
        this.consumidorId = consumidorId;
        this.mensaje = mensaje;
    }

    public Integer getIdComentarios() {
        return idComentarios;
    }

    public void setIdComentarios(Integer idComentarios) {
        this.idComentarios = idComentarios;
    }

    public int getConsumidorId() {
        return consumidorId;
    }

    public void setConsumidorId(int consumidorId) {
        this.consumidorId = consumidorId;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Supermercados getSupermercadoId() {
        return supermercadoId;
    }

    public void setSupermercadoId(Supermercados supermercadoId) {
        this.supermercadoId = supermercadoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComentarios != null ? idComentarios.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comentarios)) {
            return false;
        }
        Comentarios other = (Comentarios) object;
        if ((this.idComentarios == null && other.idComentarios != null) || (this.idComentarios != null && !this.idComentarios.equals(other.idComentarios))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_supermercados.Comentarios[ idComentarios=" + idComentarios + " ]";
    }

}
