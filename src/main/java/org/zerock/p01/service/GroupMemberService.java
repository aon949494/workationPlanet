package org.zerock.p01.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.p01.dto.GroupMemberDTO;

public interface GroupMemberService {
    Long groupJoin(GroupMemberDTO groupMemberDTO);
    void groupDelete(String memberId,String groupName);
}