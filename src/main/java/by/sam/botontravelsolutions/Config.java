package by.sam.botontravelsolutions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    public static final String CONFIGURATION_BOT_FILE = "src/main/resources/bot/bot.properties";
    public static final String CONFIGURATION_DB_FILE = "src/main/resources/database/database.properties";

    public static String BOT_NAME;
    public static String BOT_TOKEN;

    public static String DB_URL;
    public static String DB_USER;
    public static String DB_PWD;

    public static void load() {

        Properties botSettings = new Properties();
        try (InputStream stream = new FileInputStream(new File(CONFIGURATION_BOT_FILE))) {
            botSettings.load(stream);
            System.out.println("Конфигурация бота загружена успешно.");
        } catch (IOException e){
            System.out.println("Ошбика загрузки конфигурации бота.");
        }

        Properties dbSettings = new Properties();
        try (InputStream stream = new FileInputStream(new File(CONFIGURATION_DB_FILE))) {
            botSettings.load(stream);
            System.out.println("Конфигурация Базы Данных загружена успешно.");
        } catch (IOException e){
            System.out.println("Ошбика загрузки конфигурации Базы Данных.");
        }

        BOT_NAME = botSettings.getProperty("BotName");
        BOT_TOKEN = botSettings.getProperty("BotToken");

        DB_URL = dbSettings.getProperty("DbUrl");
        DB_USER = dbSettings.getProperty("DbUser");
        DB_PWD=dbSettings.getProperty("DbPwd");
    }
}
