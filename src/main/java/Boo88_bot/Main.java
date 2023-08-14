package Boo88_bot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {

    public static long userID;
    public static Bot bot;

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        bot = new Bot();
        botsApi.registerBot(bot);

    }

    public static void getUserList(int n) {
        Controller controller = new Controller(bot);
        for (String str : controller.getUsers(n)) {
            bot.sendText(userID, str);
        }
    }

    public static void getUserByID(int id) {
        Controller controller = new Controller(bot);
        bot.sendText(userID, controller.getUserID(id));
    }
}