/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package colas.supermercados;

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
            System.out.println("Productos; " + productoCola.listar()[0].getNombre());
        } catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
            System.out.println("Error; " + e.getMessage());
        }
    }

}
