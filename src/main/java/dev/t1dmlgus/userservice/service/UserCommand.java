package dev.t1dmlgus.userservice.service;

import dev.t1dmlgus.userservice.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class UserCommand {

    @ToString
    @Getter
    public static class joinUser {
        private final String name;
        private final String email;
        private final String pwd;

        @Builder
        private joinUser(String name, String email, String pwd) {
            this.name = name;
            this.email = email;
            this.pwd = pwd;
        }

        public static joinUser newInstance(String name, String email, String pwd){
            return joinUser.builder()
                    .name(name)
                    .email(email)
                    .pwd(pwd)
                    .build();
        }

        public User toUser() {
            return User.newInstance(name, email, pwd);
        }
    }


}
