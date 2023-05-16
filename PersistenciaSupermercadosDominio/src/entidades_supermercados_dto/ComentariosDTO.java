/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades_supermercados_dto;

import entidades_supermercados.Supermercados;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author USER
 */
public class ComentariosDTO implements Serializable{

    private Integer idComentarios;
    private int consumidorId;
    private String mensaje;
    private Supermercados supermercadoId;

    public ComentariosDTO(Integer idComentarios, int consumidorId, String mensaje, Supermercados supermercadoId) {
        this.idComentarios = idComentarios;
        this.consumidorId = consumidorId;
        this.mensaje = mensaje;
        supermercadoId.setComentariosList(null);
        supermercadoId.setProductosList(null);
        this.supermercadoId = supermercadoId;
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
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.idComentarios);
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
        final ComentariosDTO other = (ComentariosDTO) obj;
        return Objects.equals(this.idComentarios, other.idComentarios);
    }


    @Override
    public String toString() {
        return "ComentariosDTO{" + "idComentarios=" + idComentarios + ", consumidorId=" + consumidorId + ", mensaje=" + mensaje + ", supermercadoId=" + supermercadoId + '}';
    }

}
