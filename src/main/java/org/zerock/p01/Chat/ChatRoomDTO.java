package org.zerock.p01.Chat;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Data
//@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Getter
public class ChatRoomDTO {
    private String roomId;
    private String name;
    private String groupName;
    private Set<WebSocketSession> sessions = new HashSet<>();
    @Builder
    public ChatRoomDTO(String roomId, String name, String groupName){
        this.roomId = roomId;
        this.name = name;
        this.groupName = groupName;
    }

}
