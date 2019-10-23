package by.sam.botontravelsolutions;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import java.util.*;

public class Bot extends TelegramLongPollingBot {
    private Set<String> userList = new LinkedHashSet<>();

    private void sendMsg(Message message, String inputLine) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(inputLine);
        try {
           sendMessage (sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();

        if (message != null && message.hasText()) {
            if (message.getText().equals("/start")) {
                sendMsg(message, "I can help you search city of dream.\n\nYou can control me by sending these commands:" + "\n/code Get promo code." + "\n/city [ Get a description of the city ].");
            }

            if (message.getText().equals("/code")) {
                if (!userList.contains(message.getChat().getUserName())) {
                    sendMsg(message, "Ваш промо-код:" + City.CodeCreate(8));
                    userList.add(message.getChat().getUserName());
                } else {
                    sendMsg(message, "Вы  уже получили промо-код.");
                }
            }

            if (message.getText().startsWith("/city ")) {
                if (message.getText().replaceAll("/city ","").equalsIgnoreCase("москва")){
                sendMsg(message, "Очень крутой город");
            } else {
                sendMsg(message, "Такого города у меня нет.");
            }
            }
        }
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
