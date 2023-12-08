package org.zerock.p01.Chat;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zerock.p01.dto.GroupDTO;
import org.zerock.p01.dto.MemberDTO;
import org.zerock.p01.repository.GroupRepository;
import org.zerock.p01.security.dto.MemberSecurityDTO;
import org.zerock.p01.service.MemberService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class ChatController {
    private final ChatService chatService;
    private final GroupRepository groupRepository;

    @GetMapping("/chat/chat")
    public void Chatting(Model model){

    }

    @RequestMapping("/chat/chatList") // 채팅방 리스트
    public String chatList(Model model){
        List<ChatRoomDTO> roomList = chatService.findAllRoom();
        model.addAttribute("roomList", roomList);
        return "chat/chatList";
    }

    @PostMapping("/chat/createRoom")  // 채팅방 생성
    public String createRoomPOST(Model model, @RequestParam String name, String username, @AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO, String groupName) {
        log.info("ChatController>> in...");
        log.info("=+=+=+=+ChatCreatePage : "+memberSecurityDTO.getMemberId());
        log.info("group>> "+groupName);
        String memberId = memberSecurityDTO.getMemberId();
        model.addAttribute("memberDTO", memberSecurityDTO);
        ChatRoomDTO room = chatService.createRoom(name, groupName);
        model.addAttribute("groupName", groupName);
//        model.addAttribute("groupList", groupRepository.findByGroupManager(memberId));
        model.addAttribute("room", room);
//        model.addAttribute("username", username);
        return "chat/chatRoom";
    }

    @GetMapping("/chat/chatRoom") // 채팅방 입장
    public String chatRoom(Model model, @RequestParam String roomId, @AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO, String groupName){
        log.info("=+=+=+=+ChatPage : "+memberSecurityDTO.getMemberId());
        log.info("group: "+groupName);
        model.addAttribute("groupName", groupName);
        model.addAttribute("memberDTO",memberSecurityDTO);
        ChatRoomDTO room = chatService.findRoomById(roomId);
        model.addAttribute("room",room);
        return "chat/chatRoom";
    }

    @GetMapping("chat/chatbot")
    public void chatbot(Model model) { // 챗봇 - openai api key 연결해야 됨
    }
}
