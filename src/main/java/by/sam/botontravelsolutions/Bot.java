package by.sam.botontravelsolutions;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.*;

public class Bot extends TelegramLongPollingBot {
    private Map<String, OpertaionProvider> opertaionMap;

    public Bot() {
        opertaionMap = new HashMap<>();
        PromoCode code = new PromoCode();
        CityProvider city = new CityProvider();
        opertaionMap.put(code.getNameOperation(), code);
        opertaionMap.put(city.getNameOperation(), city);
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

            } else if (opertaionMap.containsKey(command)) {

                if (atrib != " ") {
                    opertaionMap.get(command).setAttribute(atrib);
                    answer=opertaionMap.get(command).getOperation();
                    System.out.println(answer);
                    sendMsg(message, answer);
                } else {
                    opertaionMap.get(command).setAttribute(message.getChat().getUserName());
                    answer =  opertaionMap.get(command).getOperation();
                    System.out.println(answer);
                    sendMsg(message,answer);
                }
            }
        } else {
            return;
        }

    }

    private String createDescriptionCommand() {
        StringBuilder builder = new StringBuilder();
        for (OpertaionProvider provider : opertaionMap.values()) {
            builder.append(provider.getNameOperation() + " " + provider.getOperationDescription() + "\n");
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
