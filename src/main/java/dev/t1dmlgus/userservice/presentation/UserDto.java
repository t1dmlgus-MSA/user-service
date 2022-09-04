package dev.t1dmlgus.userservice.presentation;

import dev.t1dmlgus.userservice.service.UserCommand;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDto {

    @ToString
    @Getter
    public static class join{

        @NotBlank(message = "이름을 입력해주세요")
        private String name;
        @NotBlank(message = "이메일을 입력해주세요")
        private String email;
        @NotBlank(message = "비밀번호를 입력해주세요")
        @Size(min = 4, message = "비밀번호는 최소 4자리 이상입니다.")
        private String pwd;

        public UserCommand.joinUser toCommand() {
            return UserCommand.joinUser.newInstance(name, email, pwd);
        }
    }
}
