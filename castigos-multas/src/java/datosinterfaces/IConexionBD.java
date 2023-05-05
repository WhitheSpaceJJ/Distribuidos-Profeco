package datosinterfaces;

import javax.persistence.EntityManager;

public interface IConexionBD {

    public EntityManager crearConexion();
}
