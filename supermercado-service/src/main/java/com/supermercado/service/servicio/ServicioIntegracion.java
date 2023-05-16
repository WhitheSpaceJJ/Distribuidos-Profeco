package com.supermercado.service.servicio;
import entidades.oficial.Consumidores;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServicioIntegracion implements IServicioIntegracion {

    @Autowired
    private RestTemplate rest;

    private final String URL_CONSUMIDORES = "http://localhost:5050/consumidor";

    /**
     * Obtiene la lista de consumidores del microservicio externo
     * consumidor-service
     *
     * @return La lista de consumidores.
     */
    @Override
    public List<Consumidores> getConsumidoresFromMicroservice() {
        return Arrays.asList(rest.getForObject(URL_CONSUMIDORES, Consumidores[].class));
    }

    /**
     * Verifica si el consumidor existe en el microservicio consumidor-serivce
     *
     * @param consumidorId El consumidor a buscar si existe.
     * @return true si existe, false en caso contrario.
     */
    @Override
    public boolean consumidorExists(int consumidorId) {
                            List<Consumidores> listaobtener2=new ArrayList<>();
        try {
                    List<Consumidores> listaobtener=getConsumidoresFromMicroservice();
                    listaobtener2=listaobtener;

        } catch (Exception e) {
            System.out.println("Error; "+e.getMessage());
        }
        for (int i = 0; i < listaobtener2.size(); i++) {
            Consumidores get = listaobtener2.get(i);
            if(get.getIdConsumidores()==consumidorId){
                return true;
            }
        }
        return false;
    }

}
