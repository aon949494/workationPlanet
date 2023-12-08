package org.zerock.p01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class MemberDTO {
    private String memberId;
    private String memberPw;
    private String memberName;
    private String affiliation;
    private String email;
    private String introduce;
    private boolean del;
}