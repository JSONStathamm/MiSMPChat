package ru.team4.mismpm.messaging;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class Message {

    private final long id;                          // Идентификатор сообщения
    private final long senderId;                    // Идентификатор отправителя
    private final String text;                      // Текст сообщения
    private final Collection<Long> attachments;     // Список вложений
    private MessageStatus status;                   // Статус сообщения
    private long date;                              // Дата отправки сообщения

    public Message(long id, long senderId, String text) {
        this.id = id;
        this.senderId = senderId;
        this.text = text;
        this.attachments = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public long getSenderId() {
        return senderId;
    }

    public String getText() {
        return text;
    }

    public Collection<Long> getAttachments() {
        return attachments;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
