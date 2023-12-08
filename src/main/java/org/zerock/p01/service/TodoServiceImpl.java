package org.zerock.p01.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.p01.Entity.Todo;
import org.zerock.p01.dto.TodoDTO;
import org.zerock.p01.repository.TodoRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class TodoServiceImpl implements TodoService {
    private final ModelMapper modelMapper;
    private final TodoRepository todoRepository;

    @Override
    public Long todoInsert(TodoDTO todoDTO){
        Todo todo = modelMapper.map(todoDTO, Todo.class);
        Long lno = todoRepository.save(todo).getLno();
        todoRepository.alter();
        todoRepository.set();
        todoRepository.updat();
        return lno;
    }

    @Override
    public TodoDTO todoReadOne(Long lno){
        Optional<Todo> result = todoRepository.findById(lno);
        Todo todo = result.orElseThrow();
        TodoDTO todoDTO = modelMapper.map(todo, TodoDTO.class);
        return todoDTO;
    }

    @Override
    public List<TodoDTO> todoReadAll(){
        List<Todo> todoList = todoRepository.findAll();
        List<TodoDTO> dtoList = todoList.stream()
                .map(todo->modelMapper.map(todo, TodoDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }


    @Override
    public void todoModify(TodoDTO todoDTO){
        Optional<Todo> result = todoRepository.findById(todoDTO.getLno());

        if(result.isPresent()){
            Todo todo = result.orElseThrow();
            todo.modify(todoDTO.getTitle(), todoDTO.getDueDate(), todoDTO.getContent(),todoDTO.isFinished());
            todoRepository.save(todo);
        }
    }

    @Override
    public void todoDelete(Long lno){
        todoRepository.deleteById(lno);
        todoRepository.alter();
        todoRepository.set();
        todoRepository.updat();
    }
}