/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package colas.profeco.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.ConnectionFactory;
import entidades.oficial.*;
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

/**
 * 
 * @author Brayan Zavala
 */
public class ProfecoCola implements AutoCloseable {

    private final com.rabbitmq.client.Connection CONNECTION;
    private final com.rabbitmq.client.Channel CHANNEL;
    private final String REQUEST_QUEUE_NAME = "rpc_queue_profeco";

    public ProfecoCola() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        CONNECTION = factory.newConnection();
        CHANNEL = CONNECTION.createChannel();
    }

    public boolean guardar(Profeco message) throws IOException, InterruptedException, ExecutionException {
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

    public boolean actualizar(Profeco message) throws IOException, InterruptedException, ExecutionException {
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

    public Profeco obtenerID(int id) throws IOException, InterruptedException, ExecutionException {
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

        final CompletableFuture<Profeco> response = new CompletableFuture<>();

        String ctag = CHANNEL.basicConsume(replyQueueName, true, (consumerTag, delivery) -> {
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                ByteArrayInputStream bis = new ByteArrayInputStream(delivery.getBody());
                ObjectInputStream ois = new ObjectInputStream(bis);
                String response2 = null;
                Profeco objeto = null;
                try {
                    response2 = (String) ois.readObject();
                } catch (IOException | ClassNotFoundException ex) {
                    System.out.println("Error; " + ex.getMessage());
                }
                if (response2 != null) {
                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        objeto = mapper.readValue(response2, Profeco.class);
                    } catch (Exception e) {
                        System.out.println("Error; " + e.getMessage());
                    }
                }
                response.complete(objeto);
            }
        }, consumerTag -> {
        });

        Profeco peticion = response.get();
        CHANNEL.basicCancel(ctag);
        return peticion;
    }

    public Profeco[] listar() throws IOException, InterruptedException, ExecutionException {
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

        final CompletableFuture<Profeco[]> response = new CompletableFuture<>();

        String ctag = CHANNEL.basicConsume(replyQueueName, true, (consumerTag, delivery) -> {
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                ByteArrayInputStream bis = new ByteArrayInputStream(delivery.getBody());
                ObjectInputStream ois = new ObjectInputStream(bis);
                String response2 = null;
                Profeco[] objeto = null;
                try {
                    response2 = (String) ois.readObject();
                } catch (IOException | ClassNotFoundException ex) {
                    System.out.println("Error; " + ex.getMessage());
                }
                if (response2 != null) {
                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        objeto = mapper.readValue(response2, Profeco[].class);
                    } catch (Exception e) {
                        System.out.println("Error; " + e.getMessage());
                    }
                }
                response.complete(objeto);
            }
        }, consumerTag -> {
        });

        Profeco[] peticion = response.get();
        CHANNEL.basicCancel(ctag);
        return peticion;
    }

    public void close() throws IOException {
        CONNECTION.close();
    }

}
