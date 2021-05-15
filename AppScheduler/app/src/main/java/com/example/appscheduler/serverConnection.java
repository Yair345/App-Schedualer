package com.example.appscheduler;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class serverConnection implements Runnable{
    private Socket socket;
    private PrintWriter printWriter;
    private String msg;

    serverConnection(String msg){
        this.msg = msg;
    }

    @Override
    public void run() {
        try {
            socket = new Socket("10.0.2.2", 34679);
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
