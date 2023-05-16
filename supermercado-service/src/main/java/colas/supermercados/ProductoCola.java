package colas.supermercados;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.ConnectionFactory;
import entidades.oficial.Productos;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class ProductoCola implements AutoCloseable {

    private final com.rabbitmq.client.Connection CONNECTION;
    private final com.rabbitmq.client.Channel CHANNEL;
    private final String REQUEST_QUEUE_NAME = "rpc_queue_productos";

    public ProductoCola() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        CONNECTION = factory.newConnection();
        CHANNEL = CONNECTION.createChannel();
    }

    public boolean guardar(Productos message) throws IOException, InterruptedException, ExecutionException {
        final String corrId = UUID.randomUUID().toString();

        String replyQueueName = CHANNEL.queueDeclare().getQueue();
        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .headers(Collections.singletonMap("clave", "guardar"))
                .build();
     String jsonString2 = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            jsonString2 = mapper.writeValueAsString(message);
//            jsonString2 = new Gson().toJson(message);

        } catch (Exception e) {
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(jsonString2);
        byte[] bytes = bos.toByteArray();

        CHANNEL.basicPublish("", REQUEST_QUEUE_NAME, props, bytes);

        final CompletableFuture<Boolean> response = new CompletableFuture<>();

        String ctag = CHANNEL.basicConsume(replyQueueName, true, (consumerTag, delivery) -> {
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                ByteArrayInputStream bis = new ByteArrayInputStream(delivery.getBody());
                ObjectInputStream ois = new ObjectInputStream(bis);
                Boolean response2 = null;
                try {
                    response2 = (Boolean) ois.readObject();
                } catch (IOException | ClassNotFoundException ex) {
                    System.out.println("Error; " + ex.getMessage());
                }
                response.complete(response2);
            }
        }, consumerTag -> {
        });

        Boolean peticion = response.get();
        CHANNEL.basicCancel(ctag);
        return peticion;
    }

    public boolean actualizar(Productos message) throws IOException, InterruptedException, ExecutionException {
        final String corrId = UUID.randomUUID().toString();

        String replyQueueName = CHANNEL.queueDeclare().getQueue();
        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .headers(Collections.singletonMap("clave", "actualizar"))
                .build();

    String jsonString2 = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            jsonString2 = mapper.writeValueAsString(message);
//            jsonString2 = new Gson().toJson(message);

        } catch (Exception e) {
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(jsonString2);
        byte[] bytes = bos.toByteArray();

        CHANNEL.basicPublish("", REQUEST_QUEUE_NAME, props, bytes);

        final CompletableFuture<Boolean> response = new CompletableFuture<>();

        String ctag = CHANNEL.basicConsume(replyQueueName, true, (consumerTag, delivery) -> {
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                ByteArrayInputStream bis = new ByteArrayInputStream(delivery.getBody());
                ObjectInputStream ois = new ObjectInputStream(bis);
                Boolean response2 = null;
                try {
                    response2 = (Boolean) ois.readObject();
                } catch (IOException | ClassNotFoundException ex) {
                    System.out.println("Error; " + ex.getMessage());
                }
                response.complete(response2);
            }
        }, consumerTag -> {
        });

        Boolean peticion = response.get();
        CHANNEL.basicCancel(ctag);
        return peticion;
    }

    public boolean eliminar(int id) throws IOException, InterruptedException, ExecutionException {
        final String corrId = UUID.randomUUID().toString();

        String replyQueueName = CHANNEL.queueDeclare().getQueue();
        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .headers(Collections.singletonMap("clave", "eliminar"))
                .build();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(id);
        byte[] bytes = bos.toByteArray();

        CHANNEL.basicPublish("", REQUEST_QUEUE_NAME, props, bytes);

        final CompletableFuture<Boolean> response = new CompletableFuture<>();

        String ctag = CHANNEL.basicConsume(replyQueueName, true, (consumerTag, delivery) -> {
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                ByteArrayInputStream bis = new ByteArrayInputStream(delivery.getBody());
                ObjectInputStream ois = new ObjectInputStream(bis);
                Boolean response2 = null;
                try {
                    response2 = (Boolean) ois.readObject();
                } catch (IOException | ClassNotFoundException ex) {
                    System.out.println("Error; " + ex.getMessage());
                }
                response.complete(response2);
            }
        }, consumerTag -> {
        });

        Boolean peticion = response.get();
        CHANNEL.basicCancel(ctag);
        return peticion;
    }

    public Productos obtenerID(int id) throws IOException, InterruptedException, ExecutionException {
        final String corrId = UUID.randomUUID().toString();

        String replyQueueName = CHANNEL.queueDeclare().getQueue();
        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .headers(Collections.singletonMap("clave", "obtener"))
                .build();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(id);
        byte[] bytes = bos.toByteArray();

        CHANNEL.basicPublish("", REQUEST_QUEUE_NAME, props, bytes);

        final CompletableFuture<Productos> response = new CompletableFuture<>();

        String ctag = CHANNEL.basicConsume(replyQueueName, true, (consumerTag, delivery) -> {
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                ByteArrayInputStream bis = new ByteArrayInputStream(delivery.getBody());
                ObjectInputStream ois = new ObjectInputStream(bis);
                String response2 = null;
                Productos objeto = null;
                try {
                     String response24 = (String) ois.readObject();
                    System.out.println(response24);
                    response2 = response24;
                } catch (IOException | ClassNotFoundException ex) {
                    System.out.println("Error; " + ex.getMessage());
                }
                if (response2 != null) {
                    try {
                        ObjectMapper mapper = new ObjectMapper();
                                                    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

                        objeto = mapper.readValue(response2, Productos.class);
                    } catch (Exception e) {
                        System.out.println("Error; " + e.getMessage());
                    }
                }
                response.complete(objeto);
            }
        }, consumerTag -> {
        });

        Productos peticion = response.get();
        CHANNEL.basicCancel(ctag);
        return peticion;
    }

    public Productos[] listar() throws IOException, InterruptedException, ExecutionException {
        final String corrId = UUID.randomUUID().toString();

        String replyQueueName = CHANNEL.queueDeclare().getQueue();
        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .headers(Collections.singletonMap("clave", "listar"))
                .build();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject("listar");
        byte[] bytes = bos.toByteArray();

        CHANNEL.basicPublish("", REQUEST_QUEUE_NAME, props, bytes);

        final CompletableFuture<Productos[]> response = new CompletableFuture<>();

        String ctag = CHANNEL.basicConsume(replyQueueName, true, (consumerTag, delivery) -> {
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                ByteArrayInputStream bis = new ByteArrayInputStream(delivery.getBody());
                ObjectInputStream ois = new ObjectInputStream(bis);
                String response2 = null;
                Productos[] objeto = null;
                try {
                    String response24 = (String) ois.readObject();
                    System.out.println(response24);
                    response2 = response24;
                } catch (IOException | ClassNotFoundException ex) {
                    System.out.println("Error; " + ex.getMessage());
                }
                if (response2 != null) {
                    try {
                        ObjectMapper mapper = new ObjectMapper();
                                                    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

                        Productos[] objetoLeido = mapper.readValue(response2, Productos[].class);
                        objeto = objetoLeido;
                    } catch (Exception e) {
                        System.out.println("Error; " + e.getMessage());
                    }
                }
                response.complete(objeto);
            }
        }, consumerTag -> {
        });

        Productos[] peticion = (Productos[]) response.get();
        CHANNEL.basicCancel(ctag);
        return peticion;
    }

    public void close() throws IOException {
        CONNECTION.close();
    }

}
