package org.zerock.p01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.zerock.p01.Entity.Group;

import java.util.List;
import java.util.Optional;


public interface GroupRepository extends JpaRepository<Group, String>, QuerydslPredicateExecutor<Group> {
    @Query(value = "select * from tbl_group where group_manager=:string",nativeQuery = true)
    List<Group> findByGroupManager(@Param("string") String letter);
    @Query(value = "select * from tbl_group where group_name=:string",nativeQuery = true)
    Optional<Group> findByGroupAll(@Param("string") String letter);

    @Query(value = "delete from tbl_group where group_manager=:string",nativeQuery = true)
    void deleteAllByGroupManager(@Param("string") String letter);
}