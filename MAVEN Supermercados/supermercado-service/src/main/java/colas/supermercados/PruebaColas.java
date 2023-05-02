/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package colas.supermercados;

import entidades.oficial.Productos;
import entidades.oficial.Supermercados;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author josej
 */
public class PruebaColas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            ProductoCola productoCola = new ProductoCola();
            SuperMercadoCola ss=new SuperMercadoCola();
            Supermercados s=new Supermercados();
            s.setNombre("2");
            s.setDireccion("34");
            System.out.println("Agregado; "+ss.guardar(s));
        } catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
            System.out.println("Error; " + e.getMessage());
        }
    }
    
}
