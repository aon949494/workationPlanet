package org.zerock.p01.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.p01.Chat.ChatRoomDTO;
import org.zerock.p01.Chat.ChatService;
import org.zerock.p01.Entity.Group;
import org.zerock.p01.Entity.GroupMember;
import org.zerock.p01.dto.GroupDTO;
import org.zerock.p01.dto.GroupMemberDTO;
import org.zerock.p01.repository.GroupMemberRepository;
import org.zerock.p01.repository.GroupRepository;
import org.zerock.p01.repository.GroupTodoRepository;
import org.zerock.p01.repository.TodoRepository;
import org.zerock.p01.security.dto.MemberSecurityDTO;
import org.zerock.p01.service.GroupMemberService;
import org.zerock.p01.service.GroupService;
import org.zerock.p01.dto.MemberDTO;
import org.zerock.p01.service.MemberService;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/memberGroup")
@Log4j2
@RequiredArgsConstructor
public class MGController {
    private final GroupService groupService;
    private final MemberService memberService;
    private final GroupRepository groupRepository;
    private final GroupMemberService groupMemberService;
    private final GroupTodoRepository groupTodoRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final ChatService chatService;
    String id;

    // main
    @GetMapping("/")
    public void mainPage(Model model){
        model.addAttribute("message", "메인 페이지");
    }

    // Member

    @GetMapping("/m_register")
    public void memberRegisterGET(Model model){
        log.info(memberService.Memberlist());
        List<String> memberIdList = new ArrayList<>();
        List<MemberDTO> memberDTOS = memberService.Memberlist();
        for(int i = 0;i<memberDTOS.size();i++){
            memberIdList.add(memberDTOS.get(i).getMemberId());
        }
        model.addAttribute("memberList",memberService.Memberlist());
        model.addAttribute("mem",memberIdList);
    }

    @PostMapping("/m_register")
    public String memberRegisterPOST(@Valid MemberDTO memberDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/memberGroup/m_register";
        }
        try {
            memberService.join(memberDTO);
        }
        catch (MemberService.MidExistException e){
            redirectAttributes.addFlashAttribute("error","memberId");
            return "redirect:/memberGroup/m_register";
        }
        redirectAttributes.addFlashAttribute("result", memberDTO.getMemberId());
        return "redirect:/memberGroup/login";
    }

    @GetMapping("/login")
    public void loginGET(String error, String logout){
        log.info("login get...");
        log.info("logout : "+ logout);

        if(logout != null){
            log.info("user logout...");
        }
    }
    @GetMapping("/memberInfo")
    public void memberInfoGET(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO, Model model){
        log.info("memberInfoPage....");
        model.addAttribute("memberDTO",memberSecurityDTO);
    }
    @RequestMapping("/checkPw")
    @ResponseBody
    public boolean checkPassword(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO,
                                 @RequestParam String inputPassword,@RequestParam String changePassword, Model model){
        log.info("checkPw 진입");
        log.info(changePassword);
        String memberId = memberSecurityDTO.getMemberId();
        boolean result = memberService.checkPw(memberId, inputPassword,changePassword);
        log.info(result);

        return result;
    }
    @RequestMapping("changeEmail")
    @ResponseBody
    public boolean changeEmail(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO,
                               @RequestParam String inputPassword,@RequestParam String changeEmail, Model model){
        log.info("changeEmail진입");
        String memberId = memberSecurityDTO.getMemberId();
        boolean result = memberService.changeEmail(memberSecurityDTO,memberId,inputPassword,changeEmail);

        return result;
    }
    @RequestMapping("/changeIntroduce")
    @ResponseBody
    public boolean changeIntroduce(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO,
                                   @RequestParam String inputPassword,@RequestParam String changeIntroduce, Model model){
        log.info("changeIntroduce진입");
        String memberId = memberSecurityDTO.getMemberId();
        boolean result = memberService.changeIntroduce(memberSecurityDTO,memberId,inputPassword,changeIntroduce);
        return result;
    }
    @RequestMapping("/memberDel")
    @ResponseBody
    public boolean memberDel(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO,
                             @RequestParam String inputPassword,Model model){
        log.info("memberDel진입");
        String memberId = memberSecurityDTO.getMemberId();
        boolean result = memberService.memberDel(memberId,inputPassword);

        return result;
    }

    @RequestMapping(value ="/GroupMemberJoin",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject GroupMemberJoin(GroupMemberDTO groupMemberDTO, String groupPw, Model model){
        log.info(groupMemberDTO.getGroupName());
        log.info(groupPw);
        log.info(groupMemberDTO.getMemberId());
        Optional<Group> group = groupRepository.findByGroupAll(groupMemberDTO.getGroupName());
        JSONObject jsonObj = new JSONObject();
        Optional<GroupMember> mem = groupMemberRepository.findByMember(groupMemberDTO.getMemberId());
        if(group.isPresent()){
            Group result = group.orElseThrow();
            log.info(result.getGroupPw().equals(groupPw));
            if (result.getGroupPw().equals(groupPw)) {
                if(mem.isPresent()){
                    jsonObj.put("result","not");
                }
                else{
                    groupMemberService.groupJoin(groupMemberDTO);
                    jsonObj.put("result","ok");
                }
            }
            else{
                jsonObj.put("result","error");
            }
        }
        else{
            jsonObj.put("result","error");
        }
        return jsonObj;
    }
    @PostMapping("/GroupMemberDelete")
    public String groupMemberDelete(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO,String groupName){
        groupMemberService.groupDelete(memberSecurityDTO.getMemberId(),groupName);
        return "redirect:/mainy";
    }


    @RequestMapping(value = "/g_register",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject groupRegisterPOST(GroupDTO groupDTO){
        JSONObject jsonObj = new JSONObject();
        log.info("dsadad"+groupDTO);
        Optional<Group> group = groupRepository.findByGroupAll(groupDTO.getGroupName());
        if(group.isPresent()){
            jsonObj.put("result","error");
        }
        else{
            GroupMemberDTO groupMemberDTO = new GroupMemberDTO();
            groupMemberDTO.setGroupName(groupDTO.getGroupName());
            groupMemberDTO.setMemberId(groupDTO.getGroupManager());
            String groupName = groupService.groupInsert(groupDTO);
            groupMemberService.groupJoin(groupMemberDTO);
            jsonObj.put("result","ok");
        }
        return jsonObj;
    }


    @RequestMapping(value = "/g_delete",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject groupDelete(String groupName, String groupPw, @AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO){
        JSONObject jsonObj = new JSONObject();
        Optional<Group> result = groupRepository.findById(groupName);
        if(result.isPresent()){
            Group group = result.orElseThrow();
            if(group.getGroupPw().equals(groupPw)){
                groupService.groupDelete(groupName);
                groupMemberService.groupDelete(memberSecurityDTO.getMemberId(),groupName);
                groupTodoRepository.deleteByGroupTodo(groupName);
                jsonObj.put("result","ok");
            }
            else{
                jsonObj.put("result","error");
            }
        }
        return jsonObj;
    }
    @GetMapping("/g_main")
    public void groupMain(String groupName, @AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO, Model model, ChatRoomDTO chatRoomDTO){
        String resultm = null;
        Optional<Group>group = groupRepository.findByGroupAll(groupName);
        Group result = group.orElseThrow();
        if(result.getGroupManager().equals(memberSecurityDTO.getMemberId())){
            resultm = "groupManager";
        }
        else{
            resultm = "groupMember";
        }
        List<ChatRoomDTO> roomList = chatService.findAllRoom();
        model.addAttribute("resultm",resultm);
        model.addAttribute("groupName",groupName);
        model.addAttribute("memberDTO",memberSecurityDTO);
        model.addAttribute("roomList", roomList);
        model.addAttribute("chatRoom", chatRoomDTO);
    }
}