package org.zerock.p01.Chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Service
public class ChatService {
    private Map<String, ChatRoomDTO> chatRooms;

    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoomDTO> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoomDTO findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }

    public ChatRoomDTO createRoom(String name, String groupName) { // groupName 가져와야지
        log.info("ChatService>> in...");
        String randomId = UUID.randomUUID().toString();
        log.info("ChatService>> randomId: "+randomId);
        log.info("ChatService>> name: "+name);
        log.info("ChatService>> groupName: "+groupName);
        ChatRoomDTO chatRoomDTO = ChatRoomDTO.builder()
                .roomId(randomId)
                .name(name)
                .groupName(groupName)
                .build();
        chatRooms.put(randomId, chatRoomDTO);
        return chatRoomDTO;
    }

}
