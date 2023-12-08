package org.zerock.p01.Entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.zerock.p01.dto.TodoDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="tbl_todolist")
//@ToString(exclude = "writer, groupName") // writer, groupName을 같이 적어도 괜찮은지? -- 안되는데..?
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Todo extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 번호
    private Long lno;

    @Column(length = 100, nullable = false) // 제목
    private String title;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd") // 기한
    private LocalDate dueDate;

    @Column(length = 50, nullable = false) // 작성자
    private String writer; // 로그인된 사용자의 memberName 자동 입력

    @Column(length = 500, nullable = false) // 세부 내용
    private String content;

    @Column(length = 10, nullable = false) // 완료 여부
    private boolean isFinished;

    public void modify(String title, LocalDate dueDate, String content, boolean isFinished) {
        this.title = title;
        this.dueDate = dueDate;
        this.content = content;
        this.isFinished = isFinished;
    }
}