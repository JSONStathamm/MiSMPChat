package ru.team4.mismpm.messaging;

public class MessageAttachment {

    private final String id;            // Идентификатор вложения
    private final String pathToFile;    // Путь к файлу на серверном хранилище

    public MessageAttachment(String id, String pathToFile) {
        this.id = id;
        this.pathToFile = pathToFile;
    }

    public String getId() {
        return id;
    }

    public String getPathToFile() {
        return pathToFile;
    }

}
