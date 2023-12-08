//package org.zerock.p01.repository;
//
//import lombok.extern.log4j.Log4j2;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.zerock.p01.Entity.Group;
//import org.zerock.p01.Entity.Member;
//import org.zerock.p01.Entity.Todo;
//import org.zerock.p01.dto.TodoDTO;
//import org.zerock.p01.service.TodoService;
//
//import java.time.LocalDate;
//import java.util.Optional;
//import java.util.UUID;
//import java.util.stream.IntStream;
//
//@SpringBootTest
//@Log4j2
//public class TodoRepositoryTests {
//    @Autowired
//    TodoRepository todoRepository;
////    @Test
////    public void insertTodoLists(){ // 일정 등록
//////        Member member = Member.builder().memberId("userID...1").build();
//////        Group group = Group.builder().groupName("groupA").build();
////
////        Todo todo = Todo.builder()
////                .title("Todo03")
////                .dueDate(LocalDate.of(2023,10,01))
//////                .writer(member)
////                .writer("user03")
////                .content("content03")
//////                .regDate(LocalDate.now())
////                .isFinished(false)
//////                .groupName(group)
////                .groupName("groupC")
////                .build();
////        todoRepository.save(todo);
////        System.out.println("insert success...");
////    }
//
//    @Test
//    public void selectList(){ // 일정 조회 (+ 전체 일정 조회)
//        Long lno = 1L;
//
//        Optional<Todo> result = todoRepository.findById(lno);
//        if(result.isPresent()){
//            Todo todo = result.get();
//            System.out.println(todo);
//        }
//    }
//
////    @Test
////    public void updateList(){ // 일정 수정
////        TodoDTO todoDTO = TodoDTO.builder()
////                .lno(3L)
////                .title("modified title")
////                .dueDate(LocalDate.of(2023, 12, 1))
////                // writer도 변경 불가
////                .content("modified content")
////                .isFinished(true)
//////                .regDate(LocalDate.of(2023,10,2))
////                // groupName은 변경 불가
////                .build();
////        Long lno = todoService
////        System.out.println(todoRepository.save(todoDTO));
////        System.out.println("update success...");
////    }
//
//    @Test
//    public void deleteList(){ // 일정 삭제
//        Long lno = 6L;
//        todoRepository.deleteById(lno);
//        System.out.println("delete success...");
//    }
//}
