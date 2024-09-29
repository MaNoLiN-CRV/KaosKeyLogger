package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class Connection extends Thread {

    private static Socket socket = null;
    private final String ipServer = "192.168.209.115";
    private final int port = 2710;
    private static DataOutputStream out = null;
    private static DataInputStream input = null;

    @Override
    public void run() {
        while (Thread.currentThread().isAlive()) {
            try {
                if (socket == null){
                    socket = new Socket(ipServer, port);
                    out = new DataOutputStream(socket.getOutputStream());
                    input = new DataInputStream(socket.getInputStream());
                }
                TimeUnit.SECONDS.sleep(10);
            } catch (IOException e) {
            } catch (InterruptedException e) {
            }
        }
    }



    public Socket getSocket() {
        return socket;
    }

    public void send(ArrayList<String> list) {
        try {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                builder.append(list.get(i));
            }
            out.writeUTF(builder.toString());
        } catch (IOException e) {
        }
    }

    public String getIpServer() {
        return ipServer;
    }

    public int getPort() {
        return port;
    }

    public boolean connectionAviable() {
        boolean correct = false;
        try {
            //VERIFY CONNECTION PING PONG MODE
            if (socket!=null && input!=null && out!=null){
                out.writeUTF("*{x}*");
                if (input.readUTF().equals("*{x}*")){
                    correct = true;
                } else if (input.readUTF().equals("{exit}")) {
                    socket = null;
                    input = null;
                    out = null;
                }
            }

        } catch (IOException e) {
        }
        return correct;
    }
}
