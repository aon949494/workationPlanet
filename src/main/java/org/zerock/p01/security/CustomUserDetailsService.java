package org.zerock.p01.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.p01.Entity.Member;
import org.zerock.p01.security.dto.MemberSecurityDTO;
import org.zerock.p01.repository.MemberRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
       public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        log.info("loadUserByUsername : "+username);
        Optional<Member> result = memberRepository.getWithRoles(username);
        if(result.isEmpty()){
            throw new UsernameNotFoundException("username not found");
        }
        Member member = result.get();
        MemberSecurityDTO memberSecurityDTO = new MemberSecurityDTO(member.getMemberId(), member.getMemberPw(), member.getMemberName(), member.getAffiliation(),
                member.getEmail(),member.getIntroduce(), member.isDel(),
                member.getRoleSet().stream().map(memberRole -> new SimpleGrantedAuthority("ROLE_"+memberRole.name())).collect(Collectors.toList()));
        return memberSecurityDTO;

    }
}