package org.zerock.p01.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.p01.dto.GroupDTO;
import org.zerock.p01.dto.MemberDTO;
import org.zerock.p01.repository.GroupMemberRepository;
import org.zerock.p01.repository.GroupRepository;
import org.zerock.p01.security.dto.MemberSecurityDTO;
import org.zerock.p01.service.GroupMemberService;
import org.zerock.p01.service.GroupService;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
@Log4j2
public class MainController {
    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/mainy")
    public void mainPage(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO, Model model){
        log.info("=+=+=+=+mianPage : "+memberSecurityDTO.getMemberId());
        String memberId = memberSecurityDTO.getMemberId();
        model.addAttribute("memberDTO",memberSecurityDTO);
        model.addAttribute("groupList",groupMemberRepository.findByGroup(memberId));
        model.addAttribute("joinedGroupList", groupMemberRepository.findByMemberId(memberId));
        //model.addAttribute("message", "메인 페이지");
    }
}