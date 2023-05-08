/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejecucionprincipal;

import rpc.ConsumidoresRPC;
import rpc.SuperMercadoRPC;
import rpc.WhislListRPC;

/**
 *
 * @author josej
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            ConsumidoresRPC consumidoresRPC=new ConsumidoresRPC();
            SuperMercadoRPC superMercadoRPC=new SuperMercadoRPC();
            WhislListRPC whislListRPC=new WhislListRPC();
            consumidoresRPC.run();
            superMercadoRPC.run();
            whislListRPC.run();
        } catch (Exception e) {
            System.out.println("Error; "+e.getMessage());
        }
    }
    
}
