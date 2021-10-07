package com.projectschedule.ruby.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 *  사용자 Entity
 *      - 사용자 id
 *      - 사용자 비밀번호
 *      - 사용자 email
 *
 *  User : Schedule = 1 : N
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @Column(name = "member_id")
    private Long id;
    @NotEmpty @Length(min = 2, max = 10)
    private String name;
    @NotEmpty @Length(min = 8)
    private String password;
    @NotEmpty @Email
    private String email;

    @OneToMany(mappedBy = "member" , cascade = CascadeType.ALL)
    private List<Schedule> scheduleList = new ArrayList<>();

    @OneToMany(mappedBy = "member" , cascade = CascadeType.ALL)
    private List<NotToDo> notToDoList = new ArrayList<>();
}