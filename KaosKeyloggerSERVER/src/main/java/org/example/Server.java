package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Server extends Thread{
    private ServerSocket ser;
    private Socket client;
    private DataOutputStream out;
    private DataInputStream in;
    private String ip;
    private final int port = 2710;

    @Override
    public void run() {
        boolean correct = true;
        while (correct){
            try {
                String text = in.readUTF();
                if (text.equals("*{x}*")){
                    out.writeUTF("*{x}*");
                }else {
                    Main.writer.saveText(text);
                }

            } catch (IOException e) {
                correct = false;
                System.out.println("ERROR IN SERVER INPUT MODULE");
            }
        }
    }

    public void serverInit(ArrayList<String> bannedIps){
        boolean connected = false;
        try {
            ser = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("INTERNAL SERVER CREATION ERROR");
        }
        while (!connected){
            try {
                TimeUnit.SECONDS.sleep(5);
                client = ser.accept();
                InetAddress ipInet = client.getInetAddress();
                String ips = ipInet.getHostAddress();
                if (bannedIps.contains(ips)){
                    System.out.println(ips + " BLOCKED");
                }else {
                    out = new DataOutputStream(client.getOutputStream());
                    in = new DataInputStream(client.getInputStream());
                    ip = ips;
                    connected = true;
                }
            } catch (IOException e) {
                System.out.println("ERROR --> " + e.getMessage());
            } catch (InterruptedException e) {
            }
        }
    }

    public String getIp() {
        return ip;
    }
    public void close(){
        try {
            out.writeUTF("{exit}");
            client.close();
            ser.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
