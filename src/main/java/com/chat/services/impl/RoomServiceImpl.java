package com.chat.services.impl;

import com.chat.entities.Message;
import com.chat.entities.Room;
import com.chat.payload.MessageRequest;
import com.chat.repositories.RoomRepository;
import com.chat.services.RoomService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room createRoom(String roomId) {
        if (roomRepository.findByRoomId(roomId) != null) {
            throw new IllegalArgumentException("Room already exists!");
        }

        Room room = new Room();
        room.setRoomId(roomId);
        return roomRepository.save(room);
    }

    @Override
    public Room getRoomById(String roomId) {
        Room room = roomRepository.findByRoomId(roomId);
        if (room == null) {
            throw new IllegalArgumentException("Room not found!");
        }
        return room;
    }

    @Override
    public List<Message> getMessages(String roomId, int page, int size) {
        Room room = getRoomById(roomId);
        List<Message> messages = room.getMessages();

        int start = Math.max(0, messages.size() - (page + 1) * size);
        int end = Math.min(messages.size(), start + size);

        return messages.subList(start, end);
    }

    @Override
    public Message sendMessage(String roomId, MessageRequest request) {
        Room room = getRoomById(roomId);

        Message message = new Message();
        message.setContent(request.getContent());
        message.setSender(request.getSender());
        message.setTimeStamp(LocalDateTime.now());

        room.getMessages().add(message);
        roomRepository.save(room);

        return message;
    }

    @Override
    public Room findByRoomId(String roomId) {
        return roomRepository.findByRoomId(roomId);
    }

    @Override
    public void addMessageToRoom(String roomId, Message message) {

        Room room = getRoomById(roomId);

        // Add the new message to the room's message list
        room.getMessages().add(message);

        // Save the updated room back to the repository
        roomRepository.save(room);
    }
}
