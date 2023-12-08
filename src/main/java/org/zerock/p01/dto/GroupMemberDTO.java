package org.zerock.p01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupMemberDTO {

    private Long mno;
    @NotNull
    private String groupName;
    @NotEmpty
    private String memberId;
}