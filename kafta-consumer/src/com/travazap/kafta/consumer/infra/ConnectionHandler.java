package com.travazap.kafta.consumer.infra;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.Socket;
import java.util.logging.Logger;

public class ConnectionHandler {
    private static String MODE_CONSUMER = "mode=consumer";

    private final Logger log;
    private final String host;
    private final Integer port;

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public ConnectionHandler(final String host, final String port) {
        this.log = Logger.getLogger(ConnectionHandler.class.getName());
        this.host = host;
        this.port = Integer.valueOf(port);
    }

    public void handleConnection() throws IOException {
        socket = createSocket (host, port);
        out = new PrintWriter(socket.getOutputStream());
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        startConnection();

//        while(true) {
//            try {
//                System.out.println(in.readLine());
//            } catch (Exception noMoreMessages) {
//                System.out.println("No more messages.");
//                break;
//            }
//        }

        log.info("Connection finished");

    }

    private Socket createSocket(final String host, final Integer port) {
        Socket clientSocket;

        try {
            clientSocket = new Socket(host, port);
        } catch (IOException e) {
            throw new IllegalStateException("Could not estabilish a connection to broker.");
        }

        return clientSocket;
    }

    private void startConnection() {
        out.println(MODE_CONSUMER);
        out.flush();
    }
}