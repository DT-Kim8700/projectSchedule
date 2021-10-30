package com.projectschedule.ruby.entity.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberDto {

    @NotEmpty @Email
    private String email;
    @NotEmpty @Length(min = 8)
    private String password;
}
