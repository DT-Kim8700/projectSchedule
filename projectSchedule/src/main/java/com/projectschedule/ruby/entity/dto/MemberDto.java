package com.projectschedule.ruby.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Getter
public class MemberDto {

    @NotEmpty @Email
    private String email;
    @NotEmpty @Length(min = 8)
    private String password;
}
