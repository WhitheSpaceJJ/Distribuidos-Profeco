/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades_consumidor_dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author USER
 */
public class ConsumidoresDTO {

    private Integer idConsumidores;
    private String nombre;
    private String contraseña;
    private String email;
    private int edad;
    private List<Wishlist> wishlistList;
    private List<Supermercadosfavoritos> supermercadosfavoritosList;

    public ConsumidoresDTO(Integer idConsumidores, String nombre, String contraseña, String email, int edad, List<Wishlist> wishlistList, List<Supermercadosfavoritos> supermercadosfavoritosList) {
        this.idConsumidores = idConsumidores;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.email = email;
        this.edad = edad;
//        wishlistList = new ArrayList<>();
        for (int i = 0; i < wishlistList.size(); i++) {
            wishlistList.get(i).setConsumidorId(null);
        }
        this.wishlistList = wishlistList;
        for (int i = 0; i < supermercadosfavoritosList.size(); i++) {
            supermercadosfavoritosList.get(i).setConsumidorId(null);
        }
        this.supermercadosfavoritosList = supermercadosfavoritosList;
    }

    public Integer getIdConsumidores() {
        return idConsumidores;
    }

    public void setIdConsumidores(Integer idConsumidores) {
        this.idConsumidores = idConsumidores;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public List<Wishlist> getWishlistList() {
        return wishlistList;
    }

    public void setWishlistList(List<Wishlist> wishlistList) {
        this.wishlistList = wishlistList;
    }

    public List<Supermercadosfavoritos> getSupermercadosfavoritosList() {
        return supermercadosfavoritosList;
    }

    public void setSupermercadosfavoritosList(List<Supermercadosfavoritos> supermercadosfavoritosList) {
        this.supermercadosfavoritosList = supermercadosfavoritosList;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.idConsumidores);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ConsumidoresDTO other = (ConsumidoresDTO) obj;
        return Objects.equals(this.idConsumidores, other.idConsumidores);
    }

    @Override
    public String toString() {
        return "ConsumidoresDTO{" + "idConsumidores=" + idConsumidores + ", nombre=" + nombre + ", contrase\u00f1a=" + contraseña + ", email=" + email + ", edad=" + edad + ", wishlistList=" + wishlistList + ", supermercadosfavoritosList=" + supermercadosfavoritosList + '}';
    }

}
