package com.projectschedule.ruby.entity;

import lombok.*;
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
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Getter
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue
    private Long id;
    @Column(unique = true) @NotEmpty @Email
    private String email;
    @NotEmpty @Length(min = 2, max = 10)
    private String name;
    @NotEmpty @Length(min = 8)
    private String password;

    @OneToMany(mappedBy = "member" , cascade = CascadeType.ALL)
    private List<Schedule> scheduleList = new ArrayList<>();

    @OneToMany(mappedBy = "member" , cascade = CascadeType.ALL)
    private List<NotToDo> notToDoList = new ArrayList<>();

    public Member(Builder builder) {
        this.email = builder.email;
        this.name = builder.name;
        this.password = builder.password;
    }

    /**
     * 빌더
     */
    public static class Builder {

        private String email;
        private String name;
        private String password;

        public Builder(){}

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Member build() {
            return new Member(this);
        }
    }
}