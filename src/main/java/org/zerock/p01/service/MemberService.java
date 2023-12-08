package org.zerock.p01.service;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.zerock.p01.Entity.Member;
import org.zerock.p01.dto.MemberDTO;
import org.zerock.p01.security.dto.MemberSecurityDTO;

import java.util.List;

public interface MemberService {
    static class MidExistException extends Exception{

    }

    void join(MemberDTO memberDTO) throws MidExistException;
    List<MemberDTO> Memberlist();
    boolean checkPw(String memberId, String inputPassword, String changePassword);
    boolean changeEmail(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO,String memberId, String inputPassword, String changeEmail);
    boolean changeIntroduce(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO,String memberId, String inputPassword, String changeIntroduce);
    boolean memberDel(String memberId,String inputPassword);
}