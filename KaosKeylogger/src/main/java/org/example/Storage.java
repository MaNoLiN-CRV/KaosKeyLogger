package org.example;

import java.util.ArrayList;

public class Storage {
    private ArrayList<String> listaKeys = new ArrayList<>();

    public void store(String key) {
        listaKeys.add(key);
        if (listaKeys.size() > 10) {
            if (Main.connect.connectionAviable()) {
                Main.connect.send(listaKeys);
                listaKeys.clear();
            }

        }

    }
}

