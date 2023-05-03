/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package datos;

import datosinterfaces.ISupermercados;
import entidades_supermercados.Supermercados;

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
        ISupermercados supermercados=new SupermercadosServicio();
        Supermercados s=new Supermercados();
        s.setDireccion("2");
        s.setNombre("2");
        System.out.println("Agregado"+supermercados.guardarSupermercados(s));
        System.out.println("Super; "+supermercados.listarTodosLosSupermercados());
    }
    
}
