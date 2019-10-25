package by.sam.botontravelsolutions;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.*;

public class Bot extends TelegramLongPollingBot {
    private Map<String, CommandProvider> commandMap;

    public Bot() {
        commandMap = new HashMap<>();
        CityProvider city = new CityProvider();
        commandMap.put(city.getNameCommand(), city);
    }


    private void sendMsg(Message message, String inputLine) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(inputLine);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        String answer;

        if (message != null && message.hasText()) {
            String command = message.getText().trim();
            String atrib = " ";
            System.out.println(command);

            if (command.contains(atrib)) {
                int index = command.indexOf(atrib) + 1;
                atrib = command.substring(index);
                command = command.replaceFirst("\\s.+", "");
            }

            if (command.equals("/start")) {
                String descriptionCommand = createDescriptionCommand();
                answer = "I can help you search city of dream.\n" + descriptionCommand;
                System.out.println(answer);
                sendMsg(message, answer);

            } else if (commandMap.containsKey(command)) {

                if (atrib != " ") {
                    commandMap.get(command).setAttribute(atrib);
                    answer = commandMap.get(command).executeCommand();
                    System.out.println(answer);
                    sendMsg(message, answer);
                } else {
                    commandMap.get(command).setAttribute(message.getChat().getUserName());
                    answer = commandMap.get(command).executeCommand();
                    System.out.println(answer);
                    sendMsg(message, answer);
                }
            }
        } else {
            return;
        }

    }

    private String createDescriptionCommand() {
        StringBuilder builder = new StringBuilder();
        for (CommandProvider provider : commandMap.values()) {
            builder.append(provider.getNameCommand() + " " + provider.getDescriptionCommand() + "\n");
        }
        return builder.toString();
    }

    @Override
    public String getBotUsername() {
        return Config.BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return Config.BOT_TOKEN;
    }
}
