package service.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class ConfigServiceReader {
    private final Properties properties;
    private final String configFilePath = "configService.properties";

    public ConfigServiceReader() {
        File ConfigFile = new File(configFilePath);

        try {

            FileInputStream configFileReader = new FileInputStream(ConfigFile);

            properties = new Properties();


            try {

                properties.load(configFileReader);

                configFileReader.close();

            } catch (IOException e) {

                System.out.println(e.getMessage());

            }

        } catch (FileNotFoundException e) {

            System.out.println(e.getMessage());

            throw new RuntimeException("config.properties not found at config file path " + configFilePath);

        }
    }

    public String getBookerBaseUrl() {

        String applicationTestUrl = properties.getProperty("baseApiUrl");

        if (applicationTestUrl != null)

            return applicationTestUrl;

        else

            throw new RuntimeException("Application url not specified in the config.properties file.");

    }

    public String postBookerAuth() {
        String applicationTestUrl = properties.getProperty("baseApiUrlByAuth");

        if (applicationTestUrl != null)

            return applicationTestUrl;

        else

            throw new RuntimeException("Application url not specified in the config.properties file.");
    }

    public String getBookingIds() {
        String applicationTestUrl = properties.getProperty("getBookingIds");

        if (applicationTestUrl != null)

            return applicationTestUrl;

        else

            throw new RuntimeException("Application url not specified in the config.properties file.");
    }

    public String getBookingId() {
        String applicationTestUrl = (properties.getProperty("getBookingId"));

        if (applicationTestUrl != null)

            return applicationTestUrl + "10";

        else

            throw new RuntimeException("Application url not specified in the config.properties file.");
    }

    public String getBookingIdNewBooking() {
        String applicationTestUrl = (properties.getProperty("getBookingId"));

        if (applicationTestUrl != null)

            return applicationTestUrl;

        else

            throw new RuntimeException("Application url not specified in the config.properties file.");
    }

    public String getUserName() {

        String username = properties.getProperty("username");

        if (username != null)

            return username;

        else

            throw new RuntimeException("username not specified in the config.properties file.");

    }

    public String getPassword() {

        String password = properties.getProperty("password");

        if (password != null)

            return password;

        else

            throw new RuntimeException("password not specified in the config.properties file.");

    }
}
