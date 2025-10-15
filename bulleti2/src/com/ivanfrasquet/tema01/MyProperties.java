package com.ivanfrasquet.tema01;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class MyProperties {
    private final Properties properties;
    private final String FILE_NAME = "config.properties";
    public MyProperties() {
        properties = new Properties();

        try(FileReader fr = new FileReader(FILE_NAME)) {
            properties.load(fr);
        } catch (IOException IOException) {

        }
        String passwd = properties.getProperty("password", "1234");

        properties.setProperty("passwd", "1234");
        try(FileWriter fw = new FileWriter("config.properties")) {
            properties.store(fw, "");
        } catch (IOException IOException) {

        }

    }
}
