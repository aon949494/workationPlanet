package org.zerock.p01.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
@ToString
public class MemberSecurityDTO extends User {
    private String memberId;
    private String memberPw;
    private String memberName;
    private String affiliation;
    private String email;
    private String introduce;
    private boolean del;
    public MemberSecurityDTO(String username, String password, String memberName, String affiliation,
                             String email,String introduce,boolean del,
                             Collection<? extends GrantedAuthority> authorities){
        super(username,password, authorities);
        this.memberId = username;
        this.memberPw = password;
        this.memberName = memberName;
        this.affiliation = affiliation;
        this.email = email;
        this.introduce = introduce;
        this.del = del;
    }
}