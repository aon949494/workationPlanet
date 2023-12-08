package org.zerock.p01.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.p01.Entity.Member;
import org.zerock.p01.Entity.MemberRole;
import org.zerock.p01.dto.MemberDTO;
import org.zerock.p01.repository.GroupMemberRepository;
import org.zerock.p01.repository.GroupRepository;
import org.zerock.p01.repository.MemberRepository;
import org.zerock.p01.security.dto.MemberSecurityDTO;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional

public class MemberServiceImpl implements MemberService {
    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;

      @Override
    public void join(MemberDTO memberDTO) throws MidExistException{
        String memberId = memberDTO.getMemberId();
        boolean exist = memberRepository.existsById(memberId);
        if(exist){
            throw new MidExistException();
        }
        Member member = modelMapper.map(memberDTO, Member.class);
        member.addRole(MemberRole.USER);
        member.changePassword(passwordEncoder.encode(memberDTO.getMemberPw()));
        memberRepository.save(member);
    }
    public List<MemberDTO> Memberlist(){
        List<Member> result = memberRepository.findAll();
        List<MemberDTO> dtoList =result.stream()
                .map(dto->modelMapper.map(dto,MemberDTO.class))
                .collect(Collectors.toList());
        log.info(dtoList);
        return dtoList;
    }


    @Override
    public boolean checkPw(String memberId, String inputPassword,String changePassword){
        log.info("checkpw진입");
        Optional<Member> result = memberRepository.findById(memberId);
        Member member = result.orElseThrow();
        boolean matches = passwordEncoder.matches(inputPassword, member.getMemberPw());
        log.info(matches);
        if(matches){
            String encPassword = passwordEncoder.encode(changePassword);
            member.changePassword(encPassword);
            log.info("비밀번호변경성공");
        }
        memberRepository.save(member);
        return matches;
    }
    @Override
    public boolean changeEmail(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO,String memberId, String inputPassword, String changeEmail){
        log.info("checkemail진입");
        Optional<Member> result = memberRepository.findById(memberId);
        Member member = result.orElseThrow();
        boolean matches = passwordEncoder.matches(inputPassword, member.getMemberPw());
        log.info(matches);
        if(matches){
            member.changeEmail(changeEmail);
            log.info("이메일변경성공");
            memberRepository.save(member);
            memberSecurityDTO.setEmail(changeEmail);
        }
        return matches;
    }
    @Override
    public boolean changeIntroduce(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO,String memberId, String inputPassword, String changeIntroduce){
        log.info("changeIntroduce진입");
        Optional<Member> result = memberRepository.findById(memberId);
        Member member = result.orElseThrow();
        boolean matches = passwordEncoder.matches(inputPassword, member.getMemberPw());
        log.info(matches);
        if(matches){
            member.changeIntroduce(changeIntroduce);
            log.info("한줄소개변경성공");
            memberRepository.save(member);
            memberSecurityDTO.setIntroduce(changeIntroduce);
        }
        return matches;
    }
    @Override
    public boolean memberDel(String memberId,String inputPassword){
        Optional<Member> result = memberRepository.findById(memberId);
        Member member = result.orElseThrow();
        boolean matches = passwordEncoder.matches(inputPassword, member.getMemberPw());
        log.info(matches);
        if(matches) {
            groupRepository.deleteAllByGroupManager(memberId);
            groupMemberRepository.deleteAllByMember(memberId);
            memberRepository.deleteById(memberId);
        }
        return matches;
    }
}