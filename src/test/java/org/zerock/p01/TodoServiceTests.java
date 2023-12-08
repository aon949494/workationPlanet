//package org.zerock.p01;
//
//import lombok.extern.log4j.Log4j2;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.zerock.p01.Entity.Todo;
//import org.zerock.p01.dto.TodoDTO;
//import org.zerock.p01.service.TodoService;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@SpringBootTest
//@Log4j2
//public class TodoServiceTests {
//    @Autowired
//    private TodoService todoService;
//
////    @Test
////    public void testInsert(){
////        TodoDTO todoDTO = TodoDTO.builder()
////                .title("Todo10")
////                .dueDate(LocalDate.of(2023, 12, 1))
////                .writer("user01")
////                .content("content07")
////                .regDate(LocalDate.now())
//////                .isFinished(false)
////                .groupName("groupD")
////                .build();
////        todoService.todoInsert(todoDTO);
////    }
//
////    @Test
////    public void testReadOne(){
////        Long lno = 3L;
////        TodoDTO todoDTO = todoService.todoReadOne(lno);
////        log.info("todoReadOne>>>>> "+todoDTO);
////        System.out.println(todoDTO);
////    }
//
//    @Test
//    public void testReadAll(){
////        List<Todo> todoList = todoService.todoReadAll();
////        System.out.println(todoList);
//        List<TodoDTO> dtoList = todoService.todoReadAll();
//        System.out.println(dtoList);
//    }
//
//    @Test
//    public void testModify(){
//        TodoDTO todoDTO = TodoDTO.builder()
//                .lno(8L)
//                .title("modified title2")
//                .dueDate(LocalDate.of(2023,10,20))
//                .content("modified content2")
//                .build();
//        todoService.todoModify(todoDTO);
//        System.out.println(todoDTO);
//    }
//
//    @Test
//    public void testDelete(){
//        Long lno = 26L;
//        todoService.todoDelete(lno);
//    }
//}
