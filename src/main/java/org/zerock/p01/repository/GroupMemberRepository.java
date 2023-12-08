package org.zerock.p01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.p01.Entity.GroupMember;

import java.util.List;
import java.util.Optional;

public interface GroupMemberRepository extends JpaRepository<GroupMember,Long> {
    @Query(value = "select * from tbl_group_mem where member_id=:string",nativeQuery = true)
    List<GroupMember> findByGroup(@Param("string") String letter);
    @Query(value = "delete from tbl_group_mem where member_id=:string and group_name=:string2",nativeQuery = true)
    void deleteByMember(@Param("string") String letter,@Param("string2") String letter2);
    @Query(value = "delete from tbl_group_mem where member_id=:string",nativeQuery = true)
    void deleteAllByMember(@Param("string") String letter);

    @Query(value = "select * from tbl_group_mem where member_id=:string",nativeQuery = true)
    Optional<GroupMember> findByMember(@Param("string") String letter);


    // joined member 조회
    List<GroupMember> findByMemberId(String memberId);
}