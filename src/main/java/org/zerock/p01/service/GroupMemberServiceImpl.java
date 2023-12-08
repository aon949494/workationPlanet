package org.zerock.p01.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.p01.Entity.GroupMember;
import org.zerock.p01.dto.GroupMemberDTO;
import org.zerock.p01.repository.GroupMemberRepository;

import javax.transaction.Transactional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class GroupMemberServiceImpl implements GroupMemberService{
    private final ModelMapper modelMapper;
    private final GroupMemberRepository groupMemberRepository;
    @Override
    public Long groupJoin(GroupMemberDTO groupMemberDTO) {
        GroupMember groupMember = modelMapper.map(groupMemberDTO,GroupMember.class);
        Long mno = groupMemberRepository.save(groupMember).getMno();
        return mno;
    }

    @Override
    public void groupDelete(String memberId,String groupName) {
        groupMemberRepository.deleteByMember(memberId,groupName);
    }
}