/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package colas.consumidor;

import java.util.Arrays;

/**
 *
 * @author josej
 */
public class PruebaConsumidoresMulticolas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            ConsumidorCola cola=new ConsumidorCola();
            System.out.println("Consumidores; "+Arrays.toString(cola.listar()));
        } catch (Exception e) {
        }
    }
    
}
