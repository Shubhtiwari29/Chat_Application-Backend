package com.chat.services;

import com.chat.entities.Message;
import com.chat.entities.Room;
import com.chat.payload.MessageRequest;
import java.util.List;

public interface RoomService {

    Room createRoom(String roomId);

    Room getRoomById(String roomId);

    List<Message> getMessages(String roomId, int page, int size);

    Message sendMessage(String roomId, MessageRequest request);

    Room findByRoomId(String roomId);

    void addMessageToRoom(String roomId, Message message);
}