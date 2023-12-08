package org.zerock.p01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.zerock.p01.Entity.GroupTodo;

import java.util.List;
import java.util.Map;

public interface GroupTodoRepository extends JpaRepository<GroupTodo, Long>, QuerydslPredicateExecutor<GroupTodo> {
    @Query(value = "select * from tbl_group_todo where group_name=:string",nativeQuery = true)
    List<Map<String, Object>> findByGroupTodo(@Param("string") String letter);
    @Query(value = "delete from tbl_group_todo where group_name=:string",nativeQuery = true)
    void deleteByGroupTodo(@Param("string") String letter);

    @Query(value = "alter table tbl_group_todo auto_increment = 1", nativeQuery = true)
    String alter();
    @Query(value = "set @COUNT=0",nativeQuery = true)
    String set();
    @Query(value = "update tbl_group_todo set glno=@count::=@count+1",nativeQuery = true)
    String updat();
}