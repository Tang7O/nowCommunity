package com.nowcoder.community.service;

import com.nowcoder.community.dao.MessageMapper;
import com.nowcoder.community.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageMapper messageMapper;

    public List<Message> findCoversations(int userId, int offset, int limit) {
        return messageMapper.selectConversations(userId, offset, limit);
    }

    public int findConversationCount(int userId) {
        return messageMapper.selectConversationCount(userId);
    }

    public List<Message> findLetters(String conversation, int offset, int limit) {
        return messageMapper.selectLetters(conversation, offset, limit);
    }

    public int findLetterCount(String conversation){
        return messageMapper.selectLettersCount(conversation);
    }

    public int findLetterUnreadCount(int userId, String conversation){
        return messageMapper.selectLetterUnreadCount(userId, conversation);
    }

}
