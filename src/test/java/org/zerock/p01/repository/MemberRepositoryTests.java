//package org.zerock.p01.repository;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.zerock.p01.Entity.Member;
//
//import java.util.Optional;
//import java.util.UUID;
//import java.util.stream.IntStream;
//
//@SpringBootTest
//public class MemberRepositoryTests {
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Test
//    public void testInsertMember(){ // 임의 멤버 추가
//        IntStream.rangeClosed(1,10).forEach(i->{
//            Member member = Member.builder()
//                    .memberId("userID..."+i)
//                    .memberPw("password..."+i)
//                    .memberName("Name..."+i)
//                    .affiliation("aff..."+i)
//                    .build();
//        });
//        System.out.println("insert success...");
//    }
//
//    @Test
//    public void registerMember(){ // 회원 가입 // 사용자로부터 값 받아오도록
//        Member member = Member.builder()
//                .memberId("userID01")
//                .memberPw("password01")
//                .memberName("user01")
//                .affiliation("companyA")
//                .build();
//        System.out.println("insert success...");
//    }
//
//    @Test
//    public void selectMember(){ // 회원 정보 조회
//        String memberId = "userID...2";
//
//        Optional<Member> result = memberRepository.findById(memberId);
//        if(result.isPresent()){
//            Member member = result.get();
//            System.out.println(member);
//        }
//    }
//
////    @Test
////    public void updateUUID(){
////        String auto = "auto";
////        boolean rememberMe = (auto!=null && auto.equals("on"));
////
////        if(rememberMe){
////            String uuid = UUID.randomUUID().toString();
////
////        }
////    }
//
//    @Test
//    public void updateMember(){ // 회원 정보 수정
//        // 조회해서 수정 페이지로 접근
//        String memberId = "userID...2";
//        Member member = Member.builder()
//                .memberId(memberId)
//                .memberPw("modifiedPW")
//                .memberName("modifiedName")
//                .affiliation("modifiedAff")
//                .build();
//            memberRepository.save(member);
//            System.out.println("update success...");
//    }
//
//    @Test
//    public void deleteMember(){ // 회원 탈퇴
//        String memberId = "userID...3";
//        memberRepository.deleteById(memberId);
//        System.out.println("delete success...");
//    }
//
//    @Test
//    public void login(String memberId, String memberPw){
////        String memberId = "userID...2";
////        String memberPw = "password...2";
//
////        Optional<Member> result = memberRepository.findById(memberId);
////        if(result.isPresent()){
////            if() {//id, pw가 일치하면
////
////            }
////        }
//    }
//
//    @Test
//    public void logout(){
//
//    }
//}
