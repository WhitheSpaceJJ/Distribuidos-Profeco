package datos;

import conexion.ConexionBD;
import datosinterfaces.IConexionBD;
import datosinterfaces.ISupermercadoFavoritoServicio;
import entidades_consumidor.Supermercadosfavoritos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class SupermercadoFavoritoServicio implements ISupermercadoFavoritoServicio {

 

    private final IConexionBD conexion;

    public SupermercadoFavoritoServicio() {
        IConexionBD conexion2 = new ConexionBD();
        this.conexion = conexion2;
    }

    @Override
    public List< Supermercadosfavoritos> listarTodosLosSupermercadosFavoritos() {
        try {
            EntityManager em = this.conexion.crearConexion();
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery< Supermercadosfavoritos> criteria = builder.createQuery(Supermercadosfavoritos.class);
            Root< Supermercadosfavoritos> root = criteria.from(Supermercadosfavoritos.class);
            criteria.select(root);
            TypedQuery< Supermercadosfavoritos> query = em.createQuery(criteria);
            return query.getResultList();
        } catch (IllegalStateException ise) {
            System.err.println("No se pudieron consultar los Supermercadosfavoritos ");
            return null;
        }
    }

    @Override
    public boolean guardarSupermercadoFavorito(Supermercadosfavoritos supermercadoFavorito) {
        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
            em.persist(supermercadoFavorito);
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException ise) {
            System.err.println("No fue posible agregar el Wishlist");
            return false;
        }
    }

    @Override
    public Supermercadosfavoritos obtenerSupermercadoFavoritoPorId(int id) {
        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
           Supermercadosfavoritos productoBD = em.find(Supermercadosfavoritos.class, id);
            em.getTransaction().commit();
            return productoBD;
        } catch (IllegalStateException ise) {
            System.err.println("No se pudo consultar el Wishlist");
            return null;
        }
    }

    @Override
    public boolean actualizarSupermercadoFavorito(Supermercadosfavoritos supermercadoFavorito) {
        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
            em.merge( supermercadoFavorito);
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException ise) {
            System.err.println("No fue posible actualizar el Wishlist");
            return false;
        }
    }

    @Override
    public boolean  eliminarSupermercadoFavorito(int id){
        try {
            EntityManager em = this.conexion.crearConexion();
         
            em.getTransaction().begin();  Supermercadosfavoritos productoBD = null;
            productoBD = em.find(Supermercadosfavoritos.class, id);
        
            em.remove(productoBD);
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException ise) {
            System.err.println("No fue posible eliminar el consumidor");
            return false;
        }
    }

}
