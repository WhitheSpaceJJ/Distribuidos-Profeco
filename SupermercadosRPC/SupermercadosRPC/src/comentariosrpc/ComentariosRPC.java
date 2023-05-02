/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package comentariosrpc;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import datos.ComentariosServicio;
import datosinterfaces.IComentarios;
import entidades_supermercados.Comentarios;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import org.apache.commons.lang3.SerializationUtils;

/**
 *
 * @author josej
 */
public class ComentariosRPC {

  
    private static final String RPC_QUEUE_NAME = "rpc_queue_comentarios";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null);
        channel.queuePurge(RPC_QUEUE_NAME);

        channel.basicQos(1);

        System.out.println(" [x] Awaiting RPC requests, supermercado");

      
        DeliverCallback deliverCallback = (var consumerTag, var delivery) -> {
            AMQP.BasicProperties replyProps = new AMQP.BasicProperties.Builder()
                    .correlationId(delivery.getProperties().getCorrelationId())
                    .build();
                 String tag = (String) delivery.getProperties().getHeaders().get("clave").toString();
            IComentarios consumidorServicio = new ComentariosServicio();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            switch (tag) {
                case "guardar":
                   Comentarios peticion = null;
                    try {
                        peticion = (  Comentarios ) SerializationUtils.deserialize(delivery.getBody());
                    } catch (RuntimeException e) {
                        System.out.println(" [.] " + e);
                    }
                    boolean agregado = false;
                    try {
                        agregado = consumidorServicio.guardarComentarios(peticion);
                    } catch (Exception e) {
                    }

                    oos.writeObject(agregado);
                    byte[] bytes = bos.toByteArray();
                    channel.basicPublish("", delivery.getProperties().getReplyTo(), replyProps, bytes);
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                    break;
                case "actualizar":
                Comentarios  peticion2 = null;
                    try {
                        peticion2 = ( Comentarios ) SerializationUtils.deserialize(delivery.getBody());
                    } catch (RuntimeException e) {
                        System.out.println(" [.] " + e);
                    }
                    boolean actualizado = false;
                    try {
                        actualizado = consumidorServicio.actualizarComentarios(peticion2);
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
                        eliminado = consumidorServicio.eliminarComentarios(peticion3);
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
                Comentarios obtener =null;
                    try {
                        obtener = consumidorServicio.obtenerComentariosPorId(peticion4);
                    } catch (Exception e) {
                    }
                    oos.writeObject(obtener);
                    byte[] bytes4 = bos.toByteArray();
                    channel.basicPublish("", delivery.getProperties().getReplyTo(), replyProps, bytes4);
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                    break;
                case "listar":
                      Comentarios [] listar = null;
                    try {
                        List< Comentarios > lista = consumidorServicio.listarTodosLosComentarios();
                        listar = new   Comentarios [lista.size()];
                        for (int i = 0; i < listar.length; i++) {
                            listar[i] = lista.get(i);
                        }
                    } catch (Exception e) {
                    }
                    oos.writeObject(listar);
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
    }
    
}
