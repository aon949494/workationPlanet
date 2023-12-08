package org.zerock.p01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class GroupDTO {
    private String groupName;
    private String groupManager;
    private String groupPw;
}
