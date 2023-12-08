package org.zerock.p01.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.p01.Entity.Member;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, String> {

    @EntityGraph(attributePaths = "roleSet")
    @Query("select m from Member m where m.memberId=:memberId")
    Optional<Member> getWithRoles(String memberId);
}
