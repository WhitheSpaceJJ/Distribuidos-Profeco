/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejecucionprincipal;

import rpc.ComentariosRPC;
import rpc.ProductosRPC;
import rpc.SupermercadosRPC;

/**
 *
 * @author josej
 */
public class Ejecucion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            ComentariosRPC comentariosRPC = new ComentariosRPC();
            ProductosRPC productosRPC = new ProductosRPC();
            SupermercadosRPC supermercadosRPC = new SupermercadosRPC();
            comentariosRPC.run();
            productosRPC.run();
            supermercadosRPC.run();
        } catch (Exception e) {
            System.out.println("Error; " + e.getMessage());
        }
    }

}
