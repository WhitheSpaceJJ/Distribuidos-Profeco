package datos;

import conexion.ConexionBD;
import datosinterfaces.IConexionBD;
import datosinterfaces.IProfeco;
import entidades_profeco.Profeco;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class ProfecoServicio implements IProfeco {

    private final IConexionBD conexion;

    public ProfecoServicio() {
        IConexionBD conexion2 = new ConexionBD();
        this.conexion = conexion2;
    }

    @Override
    public List<Profeco> listarProfeco() {
        try {
            EntityManager em = this.conexion.crearConexion();
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Profeco> criteria = builder.createQuery(Profeco.class);
            Root<Profeco> root = criteria.from(Profeco.class);
            criteria.select(root);
            TypedQuery<Profeco> query = em.createQuery(criteria);
            return query.getResultList();
        } catch (IllegalStateException ise) {
            System.err.println("No se pudieron consultar la profeco " + ise);
            return null;
        }
    }

    @Override
    public boolean guardarProfeco(Profeco profeco) {
        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
            em.persist(profeco);
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException ise) {
            System.err.println("No fue posible agregar la profeco " + ise);
            return false;
        }
    }

    @Override
    public Profeco obtenerProfecoPorId(int id) {
        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
            Profeco profecoBD = em.find(Profeco.class, id);
            em.getTransaction().commit();
            return profecoBD;
        } catch (IllegalStateException ise) {
            System.err.println("No se encontró la profeco dada por id " + ise);
            return null;
        }
    }

    @Override
    public boolean actualizarProfeco(Profeco profeco) {
        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
            em.merge(profeco);
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException ise) {
            System.err.println("No fue posible actualizar la profeco " + ise);
            return false;
        }
    }

    @Override
    public boolean eliminarProfeco(int id) {
        try {
            EntityManager em = this.conexion.crearConexion();

            em.getTransaction().begin();
            Profeco profecoBD = null;
            profecoBD = em.find(Profeco.class, id);
            em.remove(profecoBD);
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException ise) {
            System.err.println("No fue posible eliminar el id de profeco dado como parametro " + ise);
            return false;
        }
    }

}
