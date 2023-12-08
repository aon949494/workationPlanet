package org.zerock.p01.Chat;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDTO {
    // 메시지 타입 : 입장, 채팅, 나감
    public enum MessageType {
        ENTER, TALK, QUIT
    }
    private ChatMessageDTO.MessageType type; // 메시지 타입
    private String roomId; // 방번호
    private String sender; // 메시지 보낸사람
    private String message; // 메시지
}
