/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rpc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import datos.WishListServicio;
import datosinterfaces.IWishListServicio;
import entidades_consumidor.Wishlist;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import org.apache.commons.lang3.SerializationUtils;

/**
 *
 * @author josej
 */
public class WhislListRPC implements Runnable {

    private static final String RPC_QUEUE_NAME = "rpc_queue_whishlist";

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

            System.out.println(" [x] Awaiting RPC requests, revistas");

            DeliverCallback deliverCallback = (var consumerTag, var delivery) -> {
                AMQP.BasicProperties replyProps = new AMQP.BasicProperties.Builder()
                        .correlationId(delivery.getProperties().getCorrelationId())
                        .build();
                String tag = (String) delivery.getProperties().getHeaders().get("clave").toString();
                IWishListServicio consumidorServicio = new WishListServicio();
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
                        Wishlist peticion = null;
                        try {
                            ObjectMapper mapper = new ObjectMapper();
                            peticion = mapper.readValue(peticion56, Wishlist.class);
                        } catch (Exception e) {
                            System.out.println("Error; " + e.getMessage());
                        }

                        boolean agregado = false;
                        try {
                            agregado = consumidorServicio.guardarWishList(peticion);
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

                        Wishlist peticion2 = null;
                        try {
                            ObjectMapper mapper = new ObjectMapper();
                            peticion2 = mapper.readValue(peticion76, Wishlist.class);
                        } catch (Exception e) {
                            System.out.println("Error; " + e.getMessage());
                        }

                        boolean actualizado = false;
                        try {
                            actualizado = consumidorServicio.actualizarWishList(peticion2);
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
                            eliminado = consumidorServicio.eliminarWishList(peticion3);
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
                        Wishlist obtener = null;
                        try {
                            obtener = consumidorServicio.obtenerWishListPorId(peticion4);
                        } catch (Exception e) {
                        }
                        String jsonString = null;
                        try {
                            ObjectMapper mapper = new ObjectMapper();
                            jsonString = mapper.writeValueAsString(obtener);
                        } catch (Exception e) {
                        }
                        oos.writeObject(jsonString);
                        byte[] bytes4 = bos.toByteArray();
                        channel.basicPublish("", delivery.getProperties().getReplyTo(), replyProps, bytes4);
                        channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                        break;
                    case "listar":
                        Wishlist[] listar = null;
                        try {
                            List<Wishlist> lista = consumidorServicio.listarTodasLasWishList();
                            listar = new Wishlist[lista.size()];
                            for (int i = 0; i < listar.length; i++) {
                                listar[i] = lista.get(i);
                            }
                        } catch (Exception e) {
                        }
                        String jsonString2 = null;
                        try {
                            ObjectMapper mapper = new ObjectMapper();
                            jsonString2 = mapper.writeValueAsString(listar);
                        } catch (Exception e) {
                        }
                        oos.writeObject(jsonString2);
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
        } catch (Exception e) {
            System.out.println("Error; " + e.getMessage());
        }

    }

}
