import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMember;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {

        try {

            User user = update.getMessage().getFrom();
            sendMsg("-1001800916951", update.getMessage().getText() + " От: " + user.getId() + " " + user.getUserName());

            GetChatMember getChatMember = new GetChatMember();
            getChatMember.setChatId("-1001930301153");
            getChatMember.setUserId(user.getId());
            ChatMember chatMember = execute(getChatMember);

            String status = chatMember.getStatus();

            if (status.equals("left")) {
                String message = "Привет! Чтобы поговорить со мной, пожалуйста, " +
                        "сначала подпишись на канал https://t.me/vozhkuhnya";
                sendMsg(update.getMessage().getChatId().toString(), message);
                return;
            }

            if (update.getMessage().getText().toLowerCase().contains("start")) {
                String message = "Привет! Я Оливье Жульенович. Если хочешь, я помогу тебе найти любую игру!" + System.lineSeparator() +
                        "Просто попроси: 'Оливье, пришли игру' и я с удовольствием выполню твою просьбу";
                sendMsg(update.getMessage().getChatId().toString(), message);
            }


            if (update.getMessage().getText().toLowerCase().contains("игр")) {

                GameParser gameParser = new GameParser();
                gameParser.parser();
                String message = gameParser.getGame().toString();

                sendMsg(update.getMessage().getChatId().toString(), message);

            } else {
                String message = "Пока я умею только присылать игры. Напиши мне 'Оливье, пришли игру'";
                sendMsg(update.getMessage().getChatId().toString(), message);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            sendMsg(chatId, "Прости, я ещё только учусь. Давай, попробуем ещё раз");
        }
    }

    @Override
    public String getBotUsername() {
        return "OlivierBot";
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }

    @Override
    public String getBotToken() {
        return "5914839278:AAFxmklLuQCAZwOqUgf_KpJyUuTRSk1uHWk";
    }
}
