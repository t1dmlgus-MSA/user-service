package dev.t1dmlgus.userservice.presentation;

import dev.t1dmlgus.userservice.service.UserCommand;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDto {

    @ToString
    @NoArgsConstructor
    @Getter
    public static class join{

        @NotBlank(message = "이름을 입력해주세요")
        private String name;
        @NotBlank(message = "이메일을 입력해주세요")
        private String email;
        @NotBlank(message = "비밀번호를 입력해주세요")
        @Size(min = 4, message = "비밀번호는 최소 4자리 이상입니다.")
        private String pwd;

        @Builder
        public join(String name, String email, String pwd) {
            this.name = name;
            this.email = email;
            this.pwd = pwd;
        }

        public UserCommand.joinUser toCommand() {
            return UserCommand.joinUser.newInstance(name, email, pwd);
        }
    }
}
