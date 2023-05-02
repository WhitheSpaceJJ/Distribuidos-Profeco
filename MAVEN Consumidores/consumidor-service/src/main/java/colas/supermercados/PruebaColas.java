/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package colas.supermercados;

import colas.consumidor.ConsumidorCola;
import entidades.oficial.Productos;
import entidades.oficial.Supermercados;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

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
            ConsumidorCola consumidorCola=new ConsumidorCola();
            System.out.println("Consumidores; "+Arrays.toString(consumidorCola.listar()));
        } catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
            System.out.println("Error; " + e.getMessage());
        }
    }
    
}
