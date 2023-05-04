/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package datos;

import datosinterfaces.IProductos;
import datosinterfaces.ISupermercados;
import entidades_supermercados.Productos;
import entidades_supermercados.Supermercados;
import java.util.Arrays;

/**
 *
 * @author josej
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        IProductos productosServer = new ProductosServicio();

        try {
            
            System.out.println(productosServer.listarTodasLasProductos());
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

    }

}
