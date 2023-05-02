package datos;

import conexion.ConexionBD;
import datosinterfaces.IComentarios;
import datosinterfaces.IConexionBD;
import entidades_consumidor.Consumidores;
import entidades_supermercados.Comentarios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class ComentariosServicio implements IComentarios{

    private final IConexionBD conexion;

    public ComentariosServicio() {
        IConexionBD conexion2 = new ConexionBD();
        this.conexion = conexion2;
    }

    @Override
    public List< Comentarios> listarTodosLosComentarios() {
        try {
            EntityManager em = this.conexion.crearConexion();
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Comentarios.class));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } catch (IllegalStateException ise) {
            System.err.println("No se pudieron consultar los Comentarios");
            return null;
        }
        
           
    }

    @Override
    public boolean guardarComentarios(Comentarios consumidor) {
        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
            em.persist(consumidor);
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException ise) {
            System.err.println("No fue posible agregar el Comentarios");
            return false;
        }
    }

    @Override
    public Comentarios obtenerComentariosPorId(int id) {

        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
          Comentarios productoBD = em.find(Comentarios.class, id);
            em.getTransaction().commit();
            return productoBD;
        } catch (IllegalStateException ise) {
            System.err.println("No se pudo consultar el Comentarios");
            return null;
        }
    }

    @Override
    public boolean actualizarComentarios(Comentarios consumidor) {
        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
            em.merge(consumidor);
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException ise) {
            System.err.println("No fue posible actualizar el Comentarios");
            return false;
        }
    }

    @Override
    public boolean eliminarComentarios(int id) {
        try {
            EntityManager em = this.conexion.crearConexion();
          
            em.getTransaction().begin();
            Comentarios productoBD = null;
            productoBD = em.find(Comentarios.class, id);
            em.remove(productoBD);
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException ise) {
            System.err.println("No fue posible eliminar el consumidor");
            return false;
        }
    }

}
