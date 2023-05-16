/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rpc;

import entidades_supermercados.Productos;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author USER
 */
public class NewMain {

  public static void main(String[] args) {
        // Crear un objeto de la clase Productos
        Productos producto = new Productos();
        producto.setIdProductos(1);
        producto.setNombre("Barritas Marinela");
        producto.setMarca("Marinela");
        producto.setPrecio(150.0);
        producto.setStock(5200);

        try {
            // Serializar el objeto
            byte[] serializedObject = serializeObject(producto);

            // Deserializar el objeto
            Productos deserializedObject = deserializeObject(serializedObject);

            // Imprimir los datos del objeto deserializado
            System.out.println("Objeto deserializado:");
            System.out.println("ID: " + deserializedObject.getIdProductos());
            System.out.println("Nombre: " + deserializedObject.getNombre());
            System.out.println("Marca: " + deserializedObject.getMarca());
            System.out.println("Precio: " + deserializedObject.getPrecio());
            System.out.println("Stock: " + deserializedObject.getStock());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Método para serializar un objeto a bytes
    private static byte[] serializeObject(Productos producto) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(producto);
        oos.flush();
        oos.close();
        bos.close();
        return bos.toByteArray();
    }

    // Método para deserializar bytes a un objeto
    private static Productos deserializeObject(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Productos producto = (Productos) ois.readObject();
        ois.close();
        bis.close();
        return producto;
    }
}
