package ru.team4.mismpm;

import com.google.gson.Gson;
import ru.team4.mismpm.authentication.AuthenticationService;
import ru.team4.mismpm.messaging.Message;
import ru.team4.mismpm.messaging.MessagingService;
import ru.team4.mismpm.messaging.channel.Channel;
import ru.team4.mismpm.messaging.channel.ChannelCategory;
import ru.team4.mismpm.messaging.channel.ChannelPost;
import ru.team4.mismpm.messaging.channel.ChannelType;
import ru.team4.mismpm.messaging.chat.Chat;
import ru.team4.mismpm.messaging.chat.ChatType;
import ru.team4.mismpm.storage.DatabaseService;
import ru.team4.mismpm.user.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringJoiner;

public class Messenger {

    public static void main(String[] args) {
        runConversationTest();
    }

    public static void runConversationTest() {

        DatabaseService databaseService = new DatabaseService();

        AuthenticationService authenticationService = new AuthenticationService(databaseService);
        MessagingService messagingService = new MessagingService(databaseService);

        // Симуляция общения в личных сообщениях

        User ivanov = authenticationService.register("ivanov@mail.com", "12345", "Иван Иванов");
        ivanov.getData().setNickname("Ivanov1337");

        User pavlov = authenticationService.register("pavlov@mail.com", "123456", "Павел Павлов");
        User sergeev = authenticationService.register("sergeev@mail.com", "1234567", "Сергей Сергеев");

        Chat conversation = messagingService.createChat(ChatType.CONVERSATION);

        conversation.getMembers().add(ivanov.getId());
        conversation.getMembers().add(pavlov.getId());
        conversation.getMembers().add(sergeev.getId());

        messagingService.sendMessage(conversation.getId(), ivanov.getId(), "Здравствуйте, коллеги!");
        messagingService.sendMessage(conversation.getId(), pavlov.getId(), "И тебе привет, Иван!");
        messagingService.sendMessage(conversation.getId(), sergeev.getId(), "Приветствую!");

        //Псевдоинтерфейс

        StringJoiner memberJoiner = new StringJoiner(", ");
        for (long memberId : conversation.getMembers()) {
            User member = databaseService.getUserStorage().get(memberId);
            memberJoiner.add(member.getData().getFullName());
        }
        System.out.printf("Участники чата: %s\n", memberJoiner);
        System.out.println("----------------------------------------------------------");
        for (long messageId : conversation.getMessages()) {
            Message message = databaseService.getMessageStorage().get(messageId);
            User sender = databaseService.getUserStorage().get(message.getSenderId());

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
            System.out.printf("\n[%s] %s: %s", simpleDateFormat.format(message.getDate()), sender.getData().getFullName(), message.getText());

        }

    }

    public static void runChannelTest() {

        DatabaseService databaseService = new DatabaseService();

        AuthenticationService authenticationService = new AuthenticationService(databaseService);
        MessagingService messagingService = new MessagingService(databaseService);

        // Симуляция ведения пользователем тематического канала

        User admin = authenticationService.register("ivanov@mail.com", "12345", "Иван Иванов");
        User subscriber1 = authenticationService.register("krutoy@mail.com", "12345", "Серёга КРУТОЙ");
        User subscriber2 = authenticationService.register("kd1345@mail.com", "12345", "Костян Дмитриев");
        User subscriber3 = authenticationService.register("inna_morozova@mail.com", "12345", "Инна Морозова");

        Channel channel = messagingService.createChannel(
                admin.getId(),
                "В мире животных",
                ChannelType.PUBLIC,
                ChannelCategory.ANIMALS,
                "https://images.com/animals/cat.png"
        );

        channel.getSubscribers().add(subscriber1.getId());
        channel.getSubscribers().add(subscriber2.getId());
        channel.getSubscribers().add(subscriber3.getId());

        ChannelPost post1 = messagingService.createChannelPost(channel.getId(), admin.getId(), "Я люблю котиков :3");

        messagingService.sendPostComment(post1.getId(), subscriber1.getId(), "Рад за тебя");
        messagingService.sendPostComment(post1.getId(), subscriber2.getId(), "Дизлайк отписка, канал СКАТИЛСЯ!!!!");
        messagingService.sendPostComment(post1.getId(), subscriber3.getId(), "Класс, я тоже! :3");

        ChannelPost post2 = messagingService.createChannelPost(channel.getId(), subscriber3.getId(), "Отдаю котят в хорошие руки! Обращаться по телефону: +7 (909) 666-13-37 ");

        messagingService.sendPostComment(post2.getId(), subscriber1.getId(), "Я как-то больше собак люблю");
        messagingService.sendPostComment(post2.getId(), subscriber2.getId(), "Забрал бы с радостью, но жена будет против......");


        //Псевдоинтерфейс

        System.out.printf("Канал \"%s\" / %s подписчик(а)\n", channel.getName(), channel.getSubscribers().size());

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");

        for (long postId : channel.getPosts()) {
            ChannelPost channelPost = databaseService.getChannelPostStorage().get(postId);
            User publisher = databaseService.getUserStorage().get(channelPost.getPublisherId());
            System.out.printf("\n============= Публикация ID: %s ==============\n\n", channelPost.getId());
            System.out.printf("[%s] %s: %s \n(Автор: %s)",
                    format.format(new Date(channelPost.getDate())),
                    channel.getName(),
                    channelPost.getText(),
                    publisher.getData().getFullName()
            );
            System.out.printf("\n\nКомментарии (%s):\n", channelPost.getComments().size());
            for (long commentId : channelPost.getComments()) {
                Message comment = databaseService.getMessageStorage().get(commentId);
                User commentator = databaseService.getUserStorage().get(comment.getSenderId());
                System.out.printf("\n   [%s] %s: %s",
                        format.format(comment.getDate()),
                        commentator.getData().getFullName(),
                        comment.getText()
                );
                channelPost.addView();
            }
            System.out.printf("\n\n======= Просмотры : %s | Лайки: %s ===========\n", channelPost.getViews(), channelPost.getLikes());
        }
    }
}