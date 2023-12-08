//package org.zerock.p01;
//
//import lombok.extern.log4j.Log4j2;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.zerock.p01.Entity.Group;
//import org.zerock.p01.dto.GroupDTO;
//import org.zerock.p01.service.GroupService;
//
//import java.util.List;
//
//@SpringBootTest
//@Log4j2
//public class GroupServiceTests {
//    @Autowired
//    private GroupService groupService;
//
//    @Test
//    public void testInsert(){
//        GroupDTO groupDTO = GroupDTO.builder()
//                .groupName("group3")
//                .groupManager("user33")
//                .groupPw("1111")
//                .build();
//        groupService.groupInsert(groupDTO);
//    }
//    @Test
//    public void testReadAll(){
//        List<GroupDTO> dtoList = groupService.groupReadAll();
//        log.info("groupReadAll>>>>>>" + dtoList);
//        System.out.println(dtoList);
//    }
//    @Test
//    public void testModify(){
//        GroupDTO groupDTO = GroupDTO.builder()
//                .groupName("group2")
//                .groupManager("user23")
//                .groupPw("2222")
//                .build();
//        groupService.groupModify(groupDTO);
//        System.out.println(groupDTO);
//    }
////    @Test
////    public void testDelete(){
////        String groupName = "group1";
////        Group groupName = Group;
////        groupService.groupDelete(groupName);
////    }
//}
//
