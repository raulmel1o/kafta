package com.travazap.kafta.producer.infra;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Logger;

public class ConnectionHandler {

    private static String MODE_PRODUCER = "mode=producer";

    private final Logger log;
    private final String host;
    private final Integer port;

    private Socket socket;
    private PrintWriter out;
    private Scanner scanner;

    public ConnectionHandler(final String host, final String port) {
        this.log = Logger.getLogger(ConnectionHandler.class.getName());
        this.host = host;
        this.port = Integer.valueOf(port);
    }

    public void handleConnection() throws IOException {
        socket = createSocket(host, port);
        out = new PrintWriter(socket.getOutputStream());
        scanner = new Scanner(System.in);

        startConnection();

        while (true) {
            log.info("Enter input");
            final String message = scanner.nextLine();
            if (message.equals("exit()")) break;

            out.println(message);
            out.flush();
        }

        log.info("Connection finished");
    }

    private Socket createSocket(final String host, final Integer port) {
        Socket clientSocket;

        try {
            clientSocket = new Socket(host, port);
        } catch (IOException e) {
            throw new IllegalStateException("Could not establish a connection to broker");
        }

        return clientSocket;
    }

    private void startConnection() {
        out.println(MODE_PRODUCER);
        out.flush();
    }
}
