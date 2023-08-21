package com.NotificationSite.NotificationSite.Form;



import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserCreateForm {
    @Size(min = 3, max = 25)
    @NotEmpty(message = "사용자ID는 필수항목입니다.")
    private String username;

    @Size(min = 10, message = "비밀번호는 10자리 이상이어야 합니다.")
    @Pattern(regexp = "^(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]+$", message = "비밀번호는 특수문자를 한번 이상 포함해야 합니다.")
    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password1;

    @NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
    private String password2;

    @NotEmpty(message = "이메일은 필수항목입니다.")
    @Email
    private String email;
}