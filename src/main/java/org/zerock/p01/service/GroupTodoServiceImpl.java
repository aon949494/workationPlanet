package org.zerock.p01.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.p01.Entity.GroupTodo;
import org.zerock.p01.dto.GroupTodoDTO;
import org.zerock.p01.repository.GroupTodoRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class GroupTodoServiceImpl implements GroupTodoService{
    private final ModelMapper modelMapper;
    private final GroupTodoRepository groupTodoRepository;
    @Override
    public Long g_todoInsert(GroupTodoDTO g_todoDTO){
        GroupTodo g_todo = modelMapper.map(g_todoDTO, GroupTodo.class);
        Long gLno = groupTodoRepository.save(g_todo).getGlno();
        groupTodoRepository.alter();
        groupTodoRepository.set();
        groupTodoRepository.updat();
        return gLno;
    }
    @Override
    public void groupTodoModify(GroupTodoDTO groupTodoDTO){
        Optional<GroupTodo> result = groupTodoRepository.findById(groupTodoDTO.getGlno());
        if(result.isPresent()){
            GroupTodo groupTodo = result.orElseThrow();
            groupTodo.modify(groupTodoDTO.getTitle(),groupTodoDTO.getDueDate(),groupTodoDTO.getContent(),groupTodoDTO.isFinished());
            groupTodoRepository.save(groupTodo);
        }
    }
    @Override
    public void groupTodoDelete(Long glno){
        groupTodoRepository.deleteById(glno);
        groupTodoRepository.alter();
        groupTodoRepository.set();
        groupTodoRepository.updat();
    }
}