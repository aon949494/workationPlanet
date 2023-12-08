package org.zerock.p01.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="tbl_group")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Group {

    @Id
    @Column (length=50, nullable=false) // 그룹 이름
    private String groupName;

    @Column (length=50, nullable=false) // 그룹 멤버 id (fk)
    private String groupManager; // 로그인된 사용자의 memberName 자동 입력 // memberId // fk

    @Column (length=50, nullable=false) // 그룹 비밀번호
    private String groupPw;

    public void modify(String groupManager, String groupPw){
        this.groupManager = groupManager;
        this.groupPw = groupPw;
    }
}