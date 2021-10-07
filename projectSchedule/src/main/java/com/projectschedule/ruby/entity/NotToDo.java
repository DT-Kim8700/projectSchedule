package com.projectschedule.ruby.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import static javax.persistence.FetchType.LAZY;

/**
 *  금지 목록 Entity
 *      - Not To Do 명
 *      - 비고
 *
 *  NotToDoList : User = N : 1
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class NotToDo {

    @Id
    @GeneratedValue
    @Column(name = "notToDo_id")
    private Long id;

    @NotEmpty
    private String notToDoName;     // Not To Do List 명
    private String remark;          // 비고

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // 연관관계 편의 메서드
    public void setMember(Member member){
        this.member = member;

        if (!member.getNotToDoList().contains(this)){
            member.getNotToDoList().add(this);
        }
    }
}
