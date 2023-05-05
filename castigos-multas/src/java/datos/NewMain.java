/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package datos;

import datosinterfaces.IProfeco;

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

        IProfeco profecoServer = new ProfecoServicio();

        try {

            System.out.println(profecoServer.listarProfeco());
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

    }

}
