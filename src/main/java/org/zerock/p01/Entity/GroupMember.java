package org.zerock.p01.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="tbl_groupMem")
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    @Column (length=50, nullable = false)
    private String memberId;

    @Column (length = 50, nullable = false)
    private String groupName;

}