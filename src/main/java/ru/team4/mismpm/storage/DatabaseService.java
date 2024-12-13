package ru.team4.mismpm.storage;

public class DatabaseService {

    private final AuthenticationStorage authenticationStorage;
    private final ChannelStorage channelStorage;
    private final ChannelPostStorage channelPostStorage;
    private final ChatStorage chatStorage;
    private final MessageStorage messageStorage;
    private final UserStorage userStorage;

    public DatabaseService() {
        authenticationStorage = new AuthenticationStorage();
        channelStorage = new ChannelStorage();
        channelPostStorage = new ChannelPostStorage();
        chatStorage = new ChatStorage();
        messageStorage = new MessageStorage();
        userStorage = new UserStorage();
    }

    public AuthenticationStorage getAuthenticationStorage() {
        return authenticationStorage;
    }

    public ChannelStorage getChannelStorage() {
        return channelStorage;
    }

    public ChannelPostStorage getChannelPostStorage() {
        return channelPostStorage;
    }

    public ChatStorage getChatStorage() {
        return chatStorage;
    }

    public MessageStorage getMessageStorage() {
        return messageStorage;
    }

    public UserStorage getUserStorage() {
        return userStorage;
    }
}
