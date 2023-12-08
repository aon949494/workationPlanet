package org.zerock.p01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupTodoDTO {
    private Long glno;
    private String title, content, memberId, groupName;
    private boolean isFinished;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dueDate;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDateTime regDate, modDate;
}
