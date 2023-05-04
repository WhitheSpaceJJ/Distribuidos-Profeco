package datos;

import conexion.ConexionBD;
import datosinterfaces.IConexionBD;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import datosinterfaces.ISupermercados;
import entidades_supermercados.Supermercados;

public class SupermercadosServicio implements ISupermercados {

 

    private final IConexionBD conexion;

    public SupermercadosServicio() {
        IConexionBD conexion2 = new ConexionBD();
        this.conexion = conexion2;
    }

    @Override
    public List< Supermercados> listarTodosLosSupermercados() {
        try {
            EntityManager em = this.conexion.crearConexion();
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery< Supermercados> criteria = builder.createQuery(Supermercados.class);
            Root< Supermercados> root = criteria.from(Supermercados.class);
            criteria.select(root);
            TypedQuery< Supermercados> query = em.createQuery(criteria);
            return query.getResultList();
        } catch (IllegalStateException ise) {
            System.err.println("No se pudieron consultar los Supermercados ");
            return null;
        }
    }

    @Override
    public boolean guardarSupermercados(Supermercados supermercadoFavorito) {
        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
            em.persist(supermercadoFavorito);
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException ise) {
            System.err.println("No fue posible agregar el Supermercados");
            return false;
        }
    }

    @Override
    public Supermercados obtenerSupermercadosPorId(int id) {
        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
      Supermercados productoBD = em.find(Supermercados.class, id);
            em.getTransaction().commit();
            return productoBD;
        } catch (IllegalStateException ise) {
            System.err.println("No se pudo consultar el Supermercados");
            return null;
        }
    }

    @Override
    public boolean actualizarSupermercados(Supermercados supermercadoFavorito) {
        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
            em.merge(supermercadoFavorito);
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException ise) {
            System.err.println("No fue posible actualizar el  Supermercados");
            return false;
        }
    }

    @Override
    public boolean  eliminarSupermercados(int id){
        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
              Supermercados  productoBD = null;
            productoBD = em.find(Supermercados.class, id);
            em.remove(productoBD);
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException ise) {
            System.err.println("No fue posible eliminar el supermercados");
            return false;
        }
    }

}
