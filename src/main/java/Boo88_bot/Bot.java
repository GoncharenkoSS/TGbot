package Boo88_bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "Boo88_bot";
    }

    @Override
    public String getBotToken() {
        return "";
    }

    @Override
    public void onUpdateReceived(Update update) {
        var msg = update.getMessage();
        var user = msg.getFrom();
        Main.userID = user.getId();
        task1(msg);
        task2(msg);
        task3(msg);
        System.out.println(user.getFirstName() + ":  " + msg.getText());
    }

    void task1(Message msg) {
        String[] parts = msg.getText().split(" ");
        if (parts[0].equals("/get_users")) {
            if (parts.length == 1) Main.getUserList(0);
            else Main.getUserList(Integer.parseInt(parts[1]));
        }
    }

    void task2(Message msg) {
        String[] parts = msg.getText().split(" ");
        if (parts[0].equals("/get_user")) {
            if (parts.length == 1) Main.getUserByID(0);
            else Main.getUserByID(Integer.parseInt(parts[1]));
        }
    }

    void task3(Message msg) {
        String[] parts = msg.getText().split(" ");
        if (parts[0].equals("/create_user")) {
            if (parts.length>1) Main.getCreateUser(parts[1], parts[2]);
        }
    }

    public void sendText(Long who, String what) {
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString()) //Who are we sending a message to
                .text(what).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }
}
