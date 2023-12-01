package org.example;

import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {
    private static Properties prop = new Properties();

    static {
        try {
            prop.load(ConfigProperties.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static String getLink() {
        return prop.get("link").toString();
    }

}
