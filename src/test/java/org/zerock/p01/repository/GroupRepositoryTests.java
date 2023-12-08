//package org.zerock.p01.repository;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.zerock.p01.Entity.Group;
//
//import java.util.Optional;
//
//@SpringBootTest
//public class GroupRepositoryTests {
//    @Autowired
//    GroupRepository groupRepository;
//
//    @Test
//    public void insertGroup(){ // 그룹 생성
//        Group group = Group.builder()
//                .groupName("groupA")
//                .groupManager("managerA")
//                .groupPw("AAA")
//                .build();
//        System.out.println("insert success...");
//    }
//
//    @Test
//    public void selectGroup(){ // 그룹 정보 조회
//        String groupName = "groupA";
//
//        Optional<Group> result = groupRepository.findById(groupName);
//        if(result.isPresent()){
//            Group group = result.get();
//            System.out.println(group);
//        }
//    }
//
//    @Test
//    public void updateGroup(){ // 그룹 정보 수정
//        String groupName = "groupA";
//        Group group = Group.builder()
//                .groupName(groupName)
//                .groupManager("managerA2")
//                .groupPw("AAAAA")
//                .build();
//        System.out.println("update success...");
//    }
//
//    @Test
//    public void deleteGroup(){ // 그룹 삭제
//        String groupName = "groupA";
//        groupRepository.deleteById(groupName);
//        System.out.println("delete success...");
//    }
//}
