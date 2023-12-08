package org.zerock.p01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.zerock.p01.Entity.Todo;

import java.util.List;
import java.util.Map;

public interface TodoRepository extends JpaRepository<Todo, Long>, QuerydslPredicateExecutor<Todo> {
    @Query(value = "select * from tbl_todolist where writer=:string",nativeQuery = true)
    List<Map<String, Object>> findByTodo(@Param("string") String letter);

    @Query(value = "alter table tbl_todolist auto_increment = 1", nativeQuery = true)
    String alter();
    @Query(value = "set @COUNT=0",nativeQuery = true)
    String set();
    @Query(value = "update tbl_todolist set lno=@count::=@count+1",nativeQuery = true)
    String updat();
}