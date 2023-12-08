package org.zerock.p01.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.p01.Entity.Group;
import org.zerock.p01.dto.GroupDTO;
import org.zerock.p01.repository.GroupRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class GroupServiceImpl implements GroupService {
    private final ModelMapper modelMapper;
    private final GroupRepository groupRepository;

    @Override
    public String groupInsert(GroupDTO groupDTO){
        Group group = modelMapper.map(groupDTO, Group.class);
        String groupName = groupRepository.save(group).getGroupName();
        return groupName;
    }

    @Override
    public GroupDTO groupReadOne(String groupName){
//        return groupRepository.findById(groupName).orElse(null);
        Optional<Group> result = groupRepository.findById(groupName);
        if(result.isPresent()){
            Group group = result.orElseThrow();
            GroupDTO groupDTO = modelMapper.map(group, GroupDTO.class);
            return groupDTO;
        }
        return null;
    }

    @Override
    public boolean groupManagerChk(String groupName, String groupManager){
        GroupDTO groupDTO = groupReadOne(groupName);
        if(groupDTO.getGroupManager().equals(groupManager)){
            return false;
        }
        return true; // true이면 해당 그룹에 속해있지 않은 사용자 - 그룹 가입 가능
    }

    @Override
    public List<GroupDTO> groupReadAll(){
        List<Group> groupList = groupRepository.findAll();
        List<GroupDTO> dtoList = groupList.stream()
                .map(group->modelMapper.map(group, GroupDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public void groupModify(GroupDTO groupDTO){
        Optional<Group> result = groupRepository.findById(groupDTO.getGroupName());

        if(result.isPresent()){
            Group group = result.orElseThrow();
            group.modify(groupDTO.getGroupManager(), groupDTO.getGroupPw());
            groupRepository.save(group);
        }
    }

    @Override
    public void groupDelete(String groupName){
        groupRepository.deleteById(groupName);
    }

    @Override
    public boolean groupCertify(String groupName, String groupPw) {
        log.info("valid method>> in...");
        Optional<Group> result = groupRepository.findById(groupName);
        log.info("valid method>> find complete...");
//        return result.filter(group -> group.getGroupPw().equals(groupPw)).isPresent();
        if(result.isPresent()){
            Group group = result.get();
            log.info("valid method>> " + group);
            if(group.getGroupPw().equals(groupPw)){
                return true;
            }
        }
        log.info("valid method>> valid failed...");
        return false;
    }
}
