/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rpc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.protocol.Warning;
import datos.ConsumidorServicio;
import datosinterfaces.IConsumidorServicio;
import entidades_consumidor.Consumidores;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import entidades_consumidor.Supermercadosfavoritos;
import entidades_consumidor.Wishlist;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import org.apache.commons.lang3.SerializationUtils;

/**
 *
 * @author josej
 */
public class ConsumidoresRPC implements Runnable {

    private static final String RPC_QUEUE_NAME = "rpc_queue_consumidor";

    @Override
    public void run() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");

            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null);
            channel.queuePurge(RPC_QUEUE_NAME);

            channel.basicQos(1);

            System.out.println(" [x] Awaiting RPC requests, consumidores");

            DeliverCallback deliverCallback = (var consumerTag, var delivery) -> {
                AMQP.BasicProperties replyProps = new AMQP.BasicProperties.Builder()
                        .correlationId(delivery.getProperties().getCorrelationId())
                        .build();
                String tag = (String) delivery.getProperties().getHeaders().get("clave").toString();
                IConsumidorServicio consumidorServicio = new ConsumidorServicio();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                switch (tag) {
                    case "guardar" -> {
                        String peticion = null;
                        try {
                            peticion = (String) SerializationUtils.deserialize(delivery.getBody());
                        } catch (RuntimeException e) {
                            System.out.println(" [.] " + e);
                        }
                        Consumidores peticion2 = null;
                        try {
                            ObjectMapper mapper = new ObjectMapper();
                            peticion2 = mapper.readValue(peticion, Consumidores.class);
                        } catch (Exception e) {
                            System.out.println("Error; " + e.getMessage());
                        }
                        boolean agregado = false;
                        try {
                            agregado = consumidorServicio.guardarConsumidor(peticion2);
                        } catch (Exception e) {
                        }

                        oos.writeObject(agregado);
                        byte[] bytes = bos.toByteArray();
                        channel.basicPublish("", delivery.getProperties().getReplyTo(), replyProps, bytes);
                        channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                    }
                    case "actualizar" -> {
                        String peticion4 = null;
                        try {
                            peticion4 = (String) SerializationUtils.deserialize(delivery.getBody());
                        } catch (RuntimeException e) {
                            System.out.println(" [.] " + e);
                        }
                        Consumidores peticion22 = null;
                        try {
                            ObjectMapper mapper = new ObjectMapper();
                            peticion22 = mapper.readValue(peticion4, Consumidores.class);
                        } catch (Exception e) {
                            System.out.println("Error; " + e.getMessage());
                        }
                        boolean actualizado = false;
                        try {
                            actualizado = consumidorServicio.actualizarConsumidor(peticion22);
                        } catch (Exception e) {
                        }
                        oos.writeObject(actualizado);
                        byte[] bytes2 = bos.toByteArray();
                        channel.basicPublish("", delivery.getProperties().getReplyTo(), replyProps, bytes2);
                        channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                    }
                    case "eliminar" -> {
                        int peticion3 = -1;
                        try {
                            peticion3 = (int) SerializationUtils.deserialize(delivery.getBody());
                        } catch (RuntimeException e) {
                            System.out.println(" [.] " + e);
                        }
                        boolean eliminado = false;
                        try {
                            eliminado = consumidorServicio.eliminarConsumidor(peticion3);
                        } catch (Exception e) {
                        }
                        oos.writeObject(eliminado);
                        byte[] bytes3 = bos.toByteArray();
                        channel.basicPublish("", delivery.getProperties().getReplyTo(), replyProps, bytes3);
                        channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                    }
                    case "obtener" -> {
                        int peticion45 = -1;
                        try {
                            peticion45 = (int) SerializationUtils.deserialize(delivery.getBody());
                        } catch (RuntimeException e) {
                            System.out.println(" [.] " + e);
                        }
                        Consumidores obtener = null;
                        try {
                            obtener = consumidorServicio.obtenerConsumidorPorId(peticion45);
                            List<Wishlist> listaW = obtener.getWishlistList();
                            List<Supermercadosfavoritos> listaS = obtener.getSupermercadosfavoritosList();
                            for (int i = 0; i < listaS.size(); i++) {
                                Supermercadosfavoritos get = listaS.get(i);
                                get.setConsumidorId(null);
                            }
                            for (int i = 0; i < listaW.size(); i++) {
                                Wishlist get = listaW.get(i);
                                get.setConsumidorId(null);
                            }
                            obtener.setSupermercadosfavoritosList(listaS);
                            obtener.setWishlistList(listaW);
                        } catch (Exception e) {
                        }
                        String jsonString = null;
                        try {
                            ObjectMapper mapper = new ObjectMapper();
                            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                            jsonString = mapper.writeValueAsString(obtener);
                        } catch (Exception e) {
                        }
                        oos.writeObject(jsonString);
                        byte[] bytes4 = bos.toByteArray();
                        channel.basicPublish("", delivery.getProperties().getReplyTo(), replyProps, bytes4);
                        channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                    }
                    case "listar" -> {
                        Consumidores[] listar = null;
                        try {
                            List<Consumidores> lista = consumidorServicio.listarTodosLosConsumidores();
                            listar = new Consumidores[lista.size()];
                            for (int i = 0; i < listar.length; i++) {
                                listar[i] = lista.get(i);
                            }
                        } catch (Exception e) {
                        }
                        ObjectMapper mapper = new ObjectMapper();
                        StringBuilder jsonBuilder = new StringBuilder();
                        for (Consumidores elemento : listar) {
                            List<Wishlist> listaW = elemento.getWishlistList();
                            List<Supermercadosfavoritos> listaS = elemento.getSupermercadosfavoritosList();
                            for (int i = 0; i < listaS.size(); i++) {
                                Supermercadosfavoritos get = listaS.get(i);
                                get.setConsumidorId(null);
                            }
                            for (int i = 0; i < listaW.size(); i++) {
                                Wishlist get = listaW.get(i);
                                get.setConsumidorId(null);
                            }
                            elemento.setSupermercadosfavoritosList(listaS);
                            elemento.setWishlistList(listaW);
                            try {
                                                            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

                                String elementoJson = mapper.writeValueAsString(elemento);
                                jsonBuilder.append(elementoJson);

                                jsonBuilder.append(",");
                            } catch (JsonProcessingException e) {
                                System.out.println("Error al convertir el elemento a JSON: " + e.getMessage());
                            }
                        }
                        if (jsonBuilder.length() > 0) {
                            jsonBuilder.setLength(jsonBuilder.length() - 1);
                        }
                        jsonBuilder.insert(0, "[");
                        jsonBuilder.append("]");
                        String jsonString3 = jsonBuilder.toString();

                        System.out.println(jsonString3);
                        oos.writeObject(jsonString3);
                        byte[] bytes5 = bos.toByteArray();
                        channel.basicPublish("", delivery.getProperties().getReplyTo(), replyProps, bytes5);
                        channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                    }
                    default ->
                        System.out.println("Tag no reconocida: " + tag);
                }
            };

            channel.basicConsume(RPC_QUEUE_NAME, false, deliverCallback, (consumerTag -> {
            }));
        } catch (Exception e) {
            System.out.println("Error; " + e.getMessage());
        }

    }

}
