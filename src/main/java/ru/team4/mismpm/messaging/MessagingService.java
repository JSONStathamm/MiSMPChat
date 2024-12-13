package ru.team4.mismpm.messaging;

import ru.team4.mismpm.messaging.channel.Channel;
import ru.team4.mismpm.messaging.channel.ChannelCategory;
import ru.team4.mismpm.messaging.channel.ChannelPost;
import ru.team4.mismpm.messaging.channel.ChannelType;
import ru.team4.mismpm.messaging.chat.Chat;
import ru.team4.mismpm.messaging.chat.ChatType;
import ru.team4.mismpm.storage.DatabaseService;

import java.util.Date;

public class MessagingService {

    private final DatabaseService databaseService;

    private long currentChatId;         // Текущий идентификатор для новосозданного чата
    private long currentChannelId;      // Текущий идентификатор для новосозданного канала
    private long currentMessageId;      // Текущий идентификатор для нового сообщения
    private long currentPostId;         // Текущий идентификатор для новой публикации

    public MessagingService(DatabaseService databaseService) {
        this.databaseService = databaseService;
        currentChatId = 0;
    }

    public Chat createChat(ChatType type) {
        Chat chat = new Chat(++currentChatId, type);
        databaseService.getChatStorage().save(currentChatId, chat);
        return chat;
    }

    public boolean addChatMember(long chatId, long memberId) {
        Chat chat = databaseService.getChatStorage().get(chatId);
        if (chat != null) {
            chat.getMembers().add(memberId);
            return true;
        }
        return false;
    }

    public boolean removeChatMember(long chatId, long memberId) {
        Chat chat = databaseService.getChatStorage().get(chatId);
        if (chat != null) {
            chat.getMembers().remove(memberId);
            return true;
        }
        return false;
    }

    public long sendMessage(long chatId, long senderId, String content) {
        Chat chat = databaseService.getChatStorage().get(chatId);
        if (chat != null) {
            Message message = new Message(++currentMessageId, senderId, content);
            message.setDate(System.currentTimeMillis());
            databaseService.getMessageStorage().save(currentMessageId, message);
            chat.getMessages().add(currentMessageId);
            return currentMessageId;
        }
        return -1;
    }

    public Channel createChannel(long ownerId, String name, ChannelType type, ChannelCategory category, String avatarUrl) {
        Channel channel = new Channel.Builder(++currentChannelId, ownerId)
                .name(name)
                .type(type)
                .category(category)
                .avatarUrl(avatarUrl)
                .build();
        databaseService.getChannelStorage().save(currentChannelId, channel);
        return channel;
    }

    public ChannelPost createChannelPost(long channelId, long publisherId, String text) {
        Channel channel = databaseService.getChannelStorage().get(channelId);
        if (channel != null) {
            ChannelPost post = new ChannelPost(++currentPostId, publisherId, System.currentTimeMillis(), text);
            databaseService.getChannelPostStorage().save(currentPostId, post);
            channel.getPosts().add(currentPostId);
            return post;
        }
        return null;
    }

    public Message sendPostComment(long postId, long senderId, String text) {
        ChannelPost post = databaseService.getChannelPostStorage().get(postId);
        if (post != null) {
            Message message = new Message(++currentMessageId, senderId, text);
            databaseService.getMessageStorage().save(currentMessageId, message);
            post.getComments().add(currentMessageId);
            return message;
        }
        return null;
    }
}
