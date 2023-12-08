package org.zerock.p01.Chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.zerock.p01.security.dto.MemberSecurityDTO;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Log4j2
@RequiredArgsConstructor
@Component
public class WebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    private HashMap<WebSocketSession, String> sessionGroupName = new HashMap<>();
    private ArrayList<String> Getsessions = new ArrayList<>();
    MemberSecurityDTO memberSecurityDTO;


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("WebSocketHandler===afterConnectionEstablished==========session=== "+session);

    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        String memberName = memberSecurityDTO.getMemberName();
        String payload = message.getPayload();
        ChatMessageDTO chatMessageDTO = objectMapper.readValue(payload, ChatMessageDTO.class);
        ChatRoomDTO roomDTO = chatService.findRoomById(chatMessageDTO.getRoomId());
        Set<WebSocketSession> sessions=roomDTO.getSessions();   //방에 있는 현재 사용자 한명의 WebsocketSession
        if (chatMessageDTO.getType().equals(ChatMessageDTO.MessageType.ENTER)) {
            //사용자가 방에 입장하면  Enter메세지를 보냄
            //socket연결은 메세지 보내기전에 이미 되어있는 상태
            sessions.add(session);
//            chatMessageDTO.setMessage(memberName + "님이 입장했습니다.");
            sendToEachSocket(sessions,new TextMessage(objectMapper.writeValueAsString(chatMessageDTO)) );
        }else if (chatMessageDTO.getType().equals(ChatMessageDTO.MessageType.QUIT)) {
            sessions.remove(session);
//            chatMessageDTO.setMessage(memberName + "님이 퇴장했습니다..");
            sendToEachSocket(sessions,new TextMessage(objectMapper.writeValueAsString(chatMessageDTO)) );
        }else {
            sendToEachSocket(sessions, message); //입장,퇴장 아닐 때는 클라이언트로부터 온 메세지 그대로 전달
        }
    }
    private  void sendToEachSocket(Set<WebSocketSession> sessions, TextMessage msg){
        sessions.parallelStream().forEach( roomSession -> {
            try {
                roomSession.sendMessage(msg);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        //javascript에서 session.close해서 연결 끊고 이 메소드 실행

    }

}