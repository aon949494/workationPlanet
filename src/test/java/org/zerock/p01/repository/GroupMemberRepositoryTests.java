//package org.zerock.p01.repository;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//public class GroupMemberRepositoryTests {
//    @Autowired
//    GroupMemberRepository groupMemberRepository;
//
////    @Test
////    public void inviteGroupMember(){ // 멤버 초대 (방장 권한)
////        // id 검색 메서드 연결
//////        Member member = Member.builder().memberId("userID...5").build();
//////        Group group = Group.builder().groupName("groupA").build();
////
////        GroupMember groupMember = GroupMember.builder()
//////                .memberId(member)
//////                .groupName(group)
////                .memberId("user01")
////                .groupName("groupA")
////                .build();
////    }
//
////    @Test
////    public void joinGroupMember(){ // 그룹 가입 (멤버)
////        // groupName 검색, groupPw 입력 메서드 연결
//////        Member member = Member.builder().memberId("userID...5").build();
//////        Group group = Group.builder().groupName("groupA").build();
////
////        GroupMember groupMember = GroupMember.builder()
//////                .memberId(member)
//////                .groupName(group)
////                .memberId("user01")
////                .groupName("groupA")
////                .build();
////    }
//
//    @Test
//    public void deleteGroupMember(){ // 그룹 나가기
//        Long mno = 5L;
//        groupMemberRepository.deleteById(mno);
//        System.out.println("delete success...");
//    }
//}
