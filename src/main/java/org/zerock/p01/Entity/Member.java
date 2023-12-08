package org.zerock.p01.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tbl_mem")
@ToString(exclude = "roleSet")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Member extends BaseEntity{
    @Id
    @Column (name="member_id", length=50, nullable=false)
    private String memberId;

    @Column (nullable=false)
    private String memberPw;

    @Column (length=50, nullable=false)
    private String memberName;

    @Column (length=50, nullable = true)
    private String affiliation;

    @Column(length = 50,nullable = false)
    private String email;

    @Column(length = 200,nullable = true)
    private String introduce;
    private boolean del;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    public void changePassword(String memberPw){this.memberPw = memberPw;}
    public void changeEmail(String email){
        this.email = email;
    }
    public void changeIntroduce(String introduce){this.introduce = introduce;}
    public void addRole(MemberRole memberRole){this.roleSet.add(memberRole);}
    public void clearRole(){this.roleSet.clear();}
    public void changeDel(boolean del){this.del = del;}


}