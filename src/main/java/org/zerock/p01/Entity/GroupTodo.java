package org.zerock.p01.Entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="tbl_groupTodo")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GroupTodo extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long glno;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 500, nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String memberId;

    @Column(length = 50, nullable = false)
    private String groupName;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    @Column(length = 10, nullable = false)
    private boolean isFinished;

    public void modify(String title,LocalDate dueDate, String content,boolean isFinished) {
        this.title = title;
        this.dueDate = dueDate;
        this.content = content;
        this.isFinished = isFinished;
    }

}