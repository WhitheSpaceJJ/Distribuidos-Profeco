/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rpc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import datos.ProductosServicio;
import datosinterfaces.IProductos;
import entidades_supermercados.Productos;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.concurrent.TimeoutException;
import org.apache.commons.lang3.SerializationUtils;

/**
 *
 * @author josej
 */
public class ProductosRPC implements Runnable {

    private static final String RPC_QUEUE_NAME = "rpc_queue_productos";

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

            System.out.println(" [x] Awaiting RPC requests, productos");

            DeliverCallback deliverCallback = (String consumerTag, Delivery delivery) -> {
                AMQP.BasicProperties replyProps = new AMQP.BasicProperties.Builder()
                        .correlationId(delivery.getProperties().getCorrelationId())
                        .build();
                String tag = (String) delivery.getProperties().getHeaders().get("clave").toString();
                IProductos consumidorServicio = new ProductosServicio();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                switch (tag) {
                    case "guardar":

                        String peticion56 = null;
                        try {
                            peticion56 = (String) SerializationUtils.deserialize(delivery.getBody());
                        } catch (RuntimeException e) {
                            System.out.println(" [.] " + e);
                        }
                        Productos peticion = null;
                        try {
                            ObjectMapper mapper = new ObjectMapper();
                            peticion = mapper.readValue(peticion56, Productos.class);
                        } catch (Exception e) {
                            System.out.println("Error; " + e.getMessage());
                        }

                        boolean agregado = false;
                        try {
                            agregado = consumidorServicio.guardarProductos(peticion);
                        } catch (Exception e) {
                        }

                        oos.writeObject(agregado);
                        byte[] bytes = bos.toByteArray();
                        channel.basicPublish("", delivery.getProperties().getReplyTo(), replyProps, bytes);
                        channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                        break;
                    case "actualizar":
                        String peticion76 = null;
                        try {
                            peticion76 = (String) SerializationUtils.deserialize(delivery.getBody());
                        } catch (RuntimeException e) {
                            System.out.println(" [.] " + e);
                        }

                        Productos peticion2 = null;
                        try {
                            ObjectMapper mapper = new ObjectMapper();
                            peticion2 = mapper.readValue(peticion76, Productos.class);
                        } catch (Exception e) {
                            System.out.println("Error; " + e.getMessage());
                        }

                        boolean actualizado = false;
                        try {
                            actualizado = consumidorServicio.actualizarProductos(peticion2);
                        } catch (Exception e) {
                        }
                        oos.writeObject(actualizado);
                        byte[] bytes2 = bos.toByteArray();
                        channel.basicPublish("", delivery.getProperties().getReplyTo(), replyProps, bytes2);
                        channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                        break;
                    case "eliminar":
                        int peticion3 = -1;
                        try {
                            peticion3 = (int) SerializationUtils.deserialize(delivery.getBody());
                        } catch (RuntimeException e) {
                            System.out.println(" [.] " + e);
                        }
                        boolean eliminado = false;
                        try {
                            eliminado = consumidorServicio.eliminarProductos(peticion3);
                        } catch (Exception e) {
                        }
                        oos.writeObject(eliminado);
                        byte[] bytes3 = bos.toByteArray();
                        channel.basicPublish("", delivery.getProperties().getReplyTo(), replyProps, bytes3);
                        channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                        break;
                    case "obtener":
                        int peticion4 = -1;
                        try {
                            peticion4 = (int) SerializationUtils.deserialize(delivery.getBody());
                        } catch (RuntimeException e) {
                            System.out.println(" [.] " + e);
                        }
                        Productos obtener = null;
                        try {
                            obtener = consumidorServicio.obtenerProductosPorId(peticion4);
                            obtener.getSupermercadoId().setComentariosList(null);
                            obtener.getSupermercadoId().setProductosList(null);
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
                        break;
                    case "listar":
                        Productos[] listar = null;
                        try {
                            List< Productos> lista = consumidorServicio.listarTodasLasProductos();
                            listar = new Productos[lista.size()];
                            for (int i = 0; i < listar.length; i++) {
                                lista.get(i).getSupermercadoId().setComentariosList(null);
                                lista.get(i).getSupermercadoId().setProductosList(null);
                                listar[i] = lista.get(i);
                            }
                        } catch (Exception e) {
                        }

                        ObjectMapper mapper = new ObjectMapper();
//                        StringBuilder jsonBuilder = new StringBuilder();
//                        for (Productos elemento : listar) {
//                            String elementoJson = mapper.writeValueAsString(elemento);
//                            jsonBuilder.append(elementoJson);
//                            jsonBuilder.append(",");
//                        }
//                        if (jsonBuilder.length() > 0) {
//                            jsonBuilder.setLength(jsonBuilder.length() - 1);
//                        }
//                        jsonBuilder.insert(0, "[");
//                        jsonBuilder.append("]");
//                        String jsonString3 = jsonBuilder.toString();
                        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

                        String jsonString3 = mapper.writeValueAsString(listar);
//                        System.out.println(jsonString3);
                        oos.writeObject(jsonString3);
                        byte[] bytes5 = bos.toByteArray();
                        channel.basicPublish("", delivery.getProperties().getReplyTo(), replyProps, bytes5);
                        channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                        break;

                    default:
                        System.out.println("Tag no reconocida: " + tag);
                }
            };

            channel.basicConsume(RPC_QUEUE_NAME, false, deliverCallback, (consumerTag -> {
            }));
        } catch (IOException | TimeoutException e) {
            System.out.println("Error; " + e.getMessage());
        }

    }

}
