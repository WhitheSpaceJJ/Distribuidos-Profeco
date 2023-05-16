package datos;

import conexion.ConexionBD;
import datosinterfaces.IConexionBD;
import datosinterfaces.IConsumidorServicio;
import entidades_consumidor.Consumidores;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class ConsumidorServicio implements IConsumidorServicio {

    private final IConexionBD conexion;

    public ConsumidorServicio() {
        IConexionBD conexion2 = new ConexionBD();
        this.conexion = conexion2;
    }

    @Override
    public List<Consumidores> listarTodosLosConsumidores() {
        try {
            EntityManager em = this.conexion.crearConexion();
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Consumidores.class));
            Query q = em.createQuery(cq);
            List<Consumidores> consumidores=q.getResultList();
        
            return consumidores;
        } catch (IllegalStateException ise) {
            System.err.println("No se pudieron consultar los Consumidores");
            return null;
        }

    }

    @Override
    public boolean guardarConsumidor(Consumidores consumidor) {
        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
            em.persist(consumidor);
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException ise) {
            System.err.println("No fue posible agregar el consumidor");
            return false;
        }
    }

    @Override
    public Consumidores obtenerConsumidorPorId(int id) {

        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
            Consumidores productoBD = em.find(Consumidores.class, id);
            em.getTransaction().commit();
            return productoBD;
        } catch (IllegalStateException ise) {
            System.err.println("No se pudo consultar el consumidor");
            return null;
        }
    }

    @Override
    public boolean actualizarConsumidor(Consumidores consumidor) {
        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
            em.merge(consumidor);
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException ise) {
            System.err.println("No fue posible actualizar el consumidor");
            return false;
        }
    }

    @Override
    public boolean eliminarConsumidor(int id) {
        try {
            EntityManager em = this.conexion.crearConexion();

            em.getTransaction().begin();
            Consumidores productoBD = null;
            productoBD = em.find(Consumidores.class, id);

            em.remove(productoBD);
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException ise) {
            System.err.println("No fue posible eliminar el consumidor");
            return false;
        }
    }

}
