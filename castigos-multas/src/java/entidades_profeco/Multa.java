package entidades_profeco;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "multa")
public class Multa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_multas", nullable = false)
    private Integer idMultas;

    @Basic(optional = false)
    @Column(name = "costo", nullable = false)
    private double costo;

    @Basic(optional = false)
    @Column(name = "motivo", nullable = false, length = 500)
    private String motivo;

    @Column(name = "supermercado_id", nullable = false)
    private int supermercadoId;

    public Multa() {
    }

    public Multa(Integer idMultas, double costo, String motivo, int supermercadoId) {
        this.idMultas = idMultas;
        this.costo = costo;
        this.motivo = motivo;
        this.supermercadoId = supermercadoId;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Integer getIdMultas() {
        return idMultas;
    }

    public void setIdMultas(Integer idMultas) {
        this.idMultas = idMultas;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public int getSupermercadoId() {
        return supermercadoId;
    }

    public void setSupermercadoId(int supermercadoId) {
        this.supermercadoId = supermercadoId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.idMultas);
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.costo) ^ (Double.doubleToLongBits(this.costo) >>> 32));
        hash = 89 * hash + Objects.hashCode(this.motivo);
        hash = 89 * hash + Objects.hashCode(this.supermercadoId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Multa other = (Multa) obj;
        if (Double.doubleToLongBits(this.costo) != Double.doubleToLongBits(other.costo)) {
            return false;
        }
        if (!Objects.equals(this.motivo, other.motivo)) {
            return false;
        }
        if (!Objects.equals(this.idMultas, other.idMultas)) {
            return false;
        }
        return Objects.equals(this.supermercadoId, other.supermercadoId);
    }

    @Override
    public String toString() {
        return "Multa{" + "idMultas=" + idMultas + ", costo=" + costo + ", motivo=" + motivo + ", supermercadoId=" + supermercadoId + '}';
    }

}
