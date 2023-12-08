package org.zerock.p01.service;

import org.zerock.p01.dto.GroupTodoDTO;
import org.zerock.p01.dto.TodoDTO;

public interface GroupTodoService {
    Long g_todoInsert(GroupTodoDTO groupTodoDTO);
    void groupTodoModify(GroupTodoDTO groupTodoDTO);
    void groupTodoDelete(Long glno);
}