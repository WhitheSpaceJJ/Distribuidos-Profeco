package datos;

import conexion.ConexionBD;
import datosinterfaces.IConexionBD;
import datosinterfaces.IProductos;

import entidades_consumidor.Wishlist;
import entidades_supermercados.Productos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class ProductosServicio implements IProductos {


  


     private final IConexionBD conexion;

    public ProductosServicio() {
        IConexionBD conexion2 = new ConexionBD();
        this.conexion = conexion2;
    }

    @Override
    public List<Productos> listarTodasLasProductos() {
         try {
        EntityManager em = this.conexion.crearConexion();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Productos> criteria = builder.createQuery(Productos.class);
        Root<Productos> root = criteria.from(Productos.class);
        criteria.select(root);
        TypedQuery<Productos> query = em.createQuery(criteria);
        return query.getResultList();
    } catch (IllegalStateException ise) {
        System.err.println("No se pudieron consultar los Productos");
        return null;
    }
    }

    @Override
    public boolean guardarProductos(Productos wishlist)  {
        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
            em.persist(wishlist);
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException ise) {
            System.err.println("No fue posible agregar el Productos");
            return false;
        }
    }

    @Override
    public Productos obtenerProductosPorId(int id) {
        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
     Productos productoBD = em.find(Productos.class, id);
            em.getTransaction().commit();
            return productoBD;
        } catch (IllegalStateException ise) {
            System.err.println("No se pudo consultar el Productos");
            return null;
        }
    }

    @Override
    public boolean actualizarProductos(Productos wishlist) {
        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
            em.merge(wishlist);
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException ise) {
            System.err.println("No fue posible actualizar el Productos");
            return false;
        }
    }

    @Override
    public boolean eliminarProductos(int id) {
        try {
            EntityManager em = this.conexion.crearConexion();

            em.getTransaction().begin();
                     Productos productoBD = null;
            productoBD = em.find(Productos.class, id);
            em.remove(productoBD);
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException ise) {
            System.err.println("No fue posible eliminar el Productos");
            return false;
        }
    }


}
