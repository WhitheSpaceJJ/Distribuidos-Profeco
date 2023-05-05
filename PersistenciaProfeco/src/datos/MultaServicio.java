package datos;

import conexion.ConexionBD;
import datosinterfaces.IConexionBD;
import datosinterfaces.IMulta;
import entidades_profeco.Multa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class MultaServicio implements IMulta {

    private final IConexionBD conexion;

    public MultaServicio() {
        IConexionBD conexion2 = new ConexionBD();
        this.conexion = conexion2;
    }

    @Override
    public List<Multa> listarTodasLasMultas() {
        try {
            EntityManager em = this.conexion.crearConexion();
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Multa> criteria = builder.createQuery(Multa.class);
            Root<Multa> root = criteria.from(Multa.class);
            criteria.select(root);
            TypedQuery<Multa> query = em.createQuery(criteria);
            return query.getResultList();
        } catch (IllegalStateException ise) {
            System.err.println("No se pudieron consultar las multas " + ise);
            return null;
        }
    }

    @Override
    public boolean guardarMulta(Multa multa) {
        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
            em.persist(multa);
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException ise) {
            System.err.println("No fue posible agregar la multa al supermercado " + ise);
            return false;
        }
    }

    @Override
    public Multa obtenerMultaPorId(int id) {
        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
            Multa multaBD = em.find(Multa.class, id);
            em.getTransaction().commit();
            return multaBD;
        } catch (IllegalStateException ise) {
            System.err.println("No se pudo consultar la multa por id " + ise);
            return null;
        }
    }

    @Override
    public boolean actualizarMulta(Multa multa) {
        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
            em.merge(multa);
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException ise) {
            System.err.println("No fue posible actualizar la multa " + ise);
            return false;
        }
    }

    @Override
    public boolean eliminarMulta(int id) {
        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
            Multa multaBD = null;
            multaBD = em.find(Multa.class, id);
            em.remove(multaBD);
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException ise) {
            System.err.println("No fue posible eliminar la multa" + ise);
            return false;
        }
    }

}
