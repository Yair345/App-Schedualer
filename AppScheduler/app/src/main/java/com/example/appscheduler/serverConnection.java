package com.example.appscheduler;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class serverConnection implements Runnable{
    /**
     * This class is used for the connection with the server
     */
    private Socket socket;
    private PrintWriter printWriter;
    private String msg;

    serverConnection(String msg){
        /**
         * Constructor
         */
        this.msg = msg;
    }

    @Override
    public void run() {
        /**
         * The actual connection
         */
        try {
            socket = new Socket("10.0.2.2", 34679); // create socket with loopback and random port
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.write(msg);
            printWriter.flush();
            printWriter.close();
            socket.shutdownOutput();
            socket.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
