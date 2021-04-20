package com.example.demo12.entities;


import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @OneToMany( cascade = CascadeType.ALL)
    private List<Message> messages;

    @Column(name = "last_message_to")
    private String lastMessageTo;

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public String getLastMessageTo() {
        return lastMessageTo;
    }

    public void setLastMessageTo(String lastMessageTo) {
        this.lastMessageTo = lastMessageTo;
    }


    public Long getUserId() {
        return userId;
    }
}
