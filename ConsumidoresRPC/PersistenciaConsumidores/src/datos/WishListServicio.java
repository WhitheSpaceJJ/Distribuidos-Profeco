package datos;

import conexion.ConexionBD;
import datosinterfaces.IConexionBD;
import datosinterfaces.IWishListServicio;
import entidades_consumidor.Wishlist;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class WishListServicio implements IWishListServicio {


  


     private final IConexionBD conexion;

    public WishListServicio() {
        IConexionBD conexion2 = new ConexionBD();
        this.conexion = conexion2;
    }

    @Override
    public List<Wishlist> listarTodasLasWishList() {
         try {
        EntityManager em = this.conexion.crearConexion();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Wishlist> criteria = builder.createQuery(Wishlist.class);
        Root<Wishlist> root = criteria.from(Wishlist.class);
        criteria.select(root);
        TypedQuery<Wishlist> query = em.createQuery(criteria);
        return query.getResultList();
    } catch (IllegalStateException ise) {
        System.err.println("No se pudieron consultar los Wishlist");
        return null;
    }
    }

    @Override
    public boolean guardarWishList(Wishlist wishlist)  {
        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
            em.persist(wishlist);
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException ise) {
            System.err.println("No fue posible agregar el Wishlist");
            return false;
        }
    }

    @Override
    public Wishlist obtenerWishListPorId(int id) {
        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
           Wishlist productoBD = em.find(Wishlist.class, id);
            em.getTransaction().commit();
            return productoBD;
        } catch (IllegalStateException ise) {
            System.err.println("No se pudo consultar el Wishlist");
            return null;
        }
    }

    @Override
    public boolean actualizarWishList(Wishlist wishlist) {
        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
            em.merge(wishlist);
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException ise) {
            System.err.println("No fue posible actualizar el Wishlist");
            return false;
        }
    }

    @Override
    public boolean eliminarWishList(int id) {
        try {
            EntityManager em = this.conexion.crearConexion();
         
          
            em.getTransaction().begin();
               Wishlist productoBD = null;
            productoBD = em.find(Wishlist.class, id);
            em.remove(productoBD);
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException ise) {
            System.err.println("No fue posible eliminar el consumidor");
            return false;
        }
    }


}
