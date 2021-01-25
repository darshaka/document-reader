package com.kn.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MessageReader {

    private static MessageReader ourInstance = new MessageReader();
    private Properties prop = new Properties();

    public static MessageReader getInstance() {
        return ourInstance;
    }

    public String getMessage(String key) {
        return this.prop.getProperty(key);
    }

    private MessageReader() {
        try (InputStream input = new FileInputStream("src/main/resources/message.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find message.properties");
                return;
            }
            this.prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
