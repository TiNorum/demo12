package com.example.demo12.entities;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @Column(name = "sent_message")
    private String sendMessage;

    @Column(name = "accepted_message")
    private String acceptedMessage;

    public String getSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(String sentMessage) {
        this.sendMessage = sentMessage;
    }

    public String getAcceptedMessage() {
        return acceptedMessage;
    }

    public void setAcceptedMessage(String acceptedMessage) {
        this.acceptedMessage = acceptedMessage;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
