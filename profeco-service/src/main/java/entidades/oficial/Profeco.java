package entidades.oficial;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "profeco")
public class Profeco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_profeco", nullable = false)
    private Integer idProfeco;
    @Basic(optional = false)
    @Column(name = "usuario", nullable = false, length = 150)
    private String usuario;
    @Basic(optional = false)
    @Column(name = "contraseña", nullable = false, length = 500)
    private String contraseña;

    public Profeco() {
    }

    public Profeco(Integer idProfeco, String usuario, String contraseña) {
        this.idProfeco = idProfeco;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public Integer getIdProfeco() {
        return idProfeco;
    }

    public void setIdProfeco(Integer idProfeco) {
        this.idProfeco = idProfeco;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "Profeco{" + "idProfeco=" + idProfeco + ", usuario=" + usuario + ", contrase\u00f1a=" + contraseña + '}';
    }

}
