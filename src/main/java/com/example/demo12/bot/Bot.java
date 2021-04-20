package com.example.demo12.bot;


import com.example.demo12.entities.DailyDomains;

import com.example.demo12.entities.Message;
import com.example.demo12.entities.User;
import com.example.demo12.repositories.DomainRepository;
import com.example.demo12.repositories.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class Bot extends TelegramLongPollingBot {
    @Autowired
    private DomainRepository domainRepository;
    @Autowired
    private UserRepository userRepository;

    @Value("${bot.name}")
    String userName;
    @Value("${bot.token}")
    String token;

    TaskTimer task;

    public Bot() throws ParseException {
        super();
        task = new TaskTimer();


       Timer timer = new Timer();
       timer.schedule(new TaskTimer(), 0, 86400000);

    }

    @Override
    public String getBotUsername() {
        return userName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Long id = update.getMessage().getChatId();
        String new_msg = update.getMessage().getText();

        Optional<User> optionalUser = userRepository.findById(id);
        User user;

        Message message = new Message();
        message.setAcceptedMessage(new_msg);


        if (optionalUser.isEmpty()) {

            user = new User();
            user.setUserId(id);

            message.setSendMessage("Hello!");
        }
        else
        {
            user = optionalUser.get();
            message.setSendMessage("Hi!");
        }

        message.setUser(user);
        user.setMessages(Arrays.asList(message));

        user.setLastMessageTo(new_msg);
        sendMessage(message.getSendMessage(), id);


        userRepository.save(user);

    }

    private boolean sendMessage(String msg, Long id) {
        try {
            SendMessage message = new SendMessage();
            message.setText(msg);
            message.setChatId(String.valueOf(id));

            execute(message);

        } catch (TelegramApiException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private class TaskTimer extends TimerTask {

        public void run() {
            URL url = null;
            try {
                url = new URL("https://backorder.ru/json/?order=desc&expired=1&by=hotness&page=1&items=50");
                File jsonFile = new File(url.getFile());
                ObjectMapper mapper = new ObjectMapper(); // just need one
                // Got a Java class that data maps to nicely? If so:

                List<DailyDomains> list = mapper.readValue(url, new TypeReference<List<DailyDomains>>() {
                });

                domainRepository.deleteAll();
                domainRepository.saveAll(list);
                domainRepository.flush();

                List<User> users  = userRepository.findAll();
                list.clear();
                list = domainRepository.findAll();



                DateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss");
                String message = dateFormatter.format(new Date())+ ". Собрано " +list.size() + " доменов.";

                for(User user: users)
                {
                    sendMessage(message,user.getUserId());
                }


            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}

