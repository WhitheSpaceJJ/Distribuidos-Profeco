/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.supermercado.cola.prod;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.ConnectionFactory;
import entidades_consumidor.Consumidores;
import entidades_supermercados.Productos;
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
 * @author josej
 */
public class ProductoCola implements AutoCloseable {

    private com.rabbitmq.client.Connection connection;
    private com.rabbitmq.client.Channel channel;
    private String requestQueueName = "rpc_queue_productos";

    public ProductoCola() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        connection = factory.newConnection();
        channel = connection.createChannel();
    }

    public boolean guardar(Productos message) throws IOException, InterruptedException, ExecutionException {
        final String corrId = UUID.randomUUID().toString();

        String replyQueueName = channel.queueDeclare().getQueue();
        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                              .headers(Collections.singletonMap("clave", "guardar"))
                .build();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(message);
        byte[] bytes = bos.toByteArray();

        channel.basicPublish("", requestQueueName, props, bytes);

        final CompletableFuture<Boolean> response = new CompletableFuture<>();

        String ctag = channel.basicConsume(replyQueueName, true, (consumerTag, delivery) -> {
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
        channel.basicCancel(ctag);
        return peticion;
    }

    public boolean actualizar(Productos message) throws IOException, InterruptedException, ExecutionException {
        final String corrId = UUID.randomUUID().toString();

        String replyQueueName = channel.queueDeclare().getQueue();
        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                              .headers(Collections.singletonMap("clave", "actualizar"))
                .build();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(message);
        byte[] bytes = bos.toByteArray();

        channel.basicPublish("", requestQueueName, props, bytes);

        final CompletableFuture<Boolean> response = new CompletableFuture<>();

        String ctag = channel.basicConsume(replyQueueName, true, (consumerTag, delivery) -> {
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
        channel.basicCancel(ctag);
        return peticion;
    }

    public boolean eliminar(int id) throws IOException, InterruptedException, ExecutionException {
        final String corrId = UUID.randomUUID().toString();

        String replyQueueName = channel.queueDeclare().getQueue();
        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                                .headers(Collections.singletonMap("clave", "eliminar"))
                .build();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(id);
        byte[] bytes = bos.toByteArray();

        channel.basicPublish("", requestQueueName, props, bytes);

        final CompletableFuture<Boolean> response = new CompletableFuture<>();

        String ctag = channel.basicConsume(replyQueueName, true, (consumerTag, delivery) -> {
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
        channel.basicCancel(ctag);
        return peticion;
    }

    public Productos obtenerID(int id) throws IOException, InterruptedException, ExecutionException {
        final String corrId = UUID.randomUUID().toString();

        String replyQueueName = channel.queueDeclare().getQueue();
        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                 .headers(Collections.singletonMap("clave", "obtener"))
                .build();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(id);
        byte[] bytes = bos.toByteArray();

        channel.basicPublish("", requestQueueName, props, bytes);

        final CompletableFuture<Productos> response = new CompletableFuture<>();

        String ctag = channel.basicConsume(replyQueueName, true, (consumerTag, delivery) -> {
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                ByteArrayInputStream bis = new ByteArrayInputStream(delivery.getBody());
                ObjectInputStream ois = new ObjectInputStream(bis);
               Productos response2 = null;
                try {
                    response2 = (Productos) ois.readObject();
                } catch (IOException | ClassNotFoundException ex) {
                    System.out.println("Error; " + ex.getMessage());
                }
                response.complete(response2);
            }
        }, consumerTag -> {
        });

      Productos peticion = response.get();
        channel.basicCancel(ctag);
        return peticion;
    }

    public Productos[] listar() throws IOException, InterruptedException, ExecutionException {
        final String corrId = UUID.randomUUID().toString();

        String replyQueueName = channel.queueDeclare().getQueue();
        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                 .headers(Collections.singletonMap("clave", "listar"))
                .build();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject("listar");
        byte[] bytes = bos.toByteArray();

        channel.basicPublish("", requestQueueName, props, bytes);

        final CompletableFuture<Productos[]> response = new CompletableFuture<>();

        String ctag = channel.basicConsume(replyQueueName, true, (consumerTag, delivery) -> {
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                ByteArrayInputStream bis = new ByteArrayInputStream(delivery.getBody());
                ObjectInputStream ois = new ObjectInputStream(bis);
                Productos[] response2 = null;
                try {
                    response2 = (Productos[]) ois.readObject();
                } catch (IOException | ClassNotFoundException ex) {
                    System.out.println("Error; " + ex.getMessage());
                }
                response.complete(response2);
            }
        }, consumerTag -> {
        });

       Productos[] peticion = response.get();
        channel.basicCancel(ctag);
        return peticion;
    }

    public void close() throws IOException {
        connection.close();
    }

}
