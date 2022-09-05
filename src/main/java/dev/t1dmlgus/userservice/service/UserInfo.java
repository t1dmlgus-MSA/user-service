package dev.t1dmlgus.userservice.service;

import dev.t1dmlgus.userservice.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserInfo {

    @Getter
    public static class UserToken{

        private final String userToken;

        @Builder
        private UserToken(String userToken) {
            this.userToken = userToken;
        }

        public static UserToken newInstance(User user){
            return UserToken.builder()
                    .userToken(user.getUserToken())
                    .build();
        }
    }

    @Getter
    public static class UserDetail {

        private final String userToken;
        private final String name;
        private final String email;
        @Builder
        public UserDetail(String userToken, String name, String email) {
            this.userToken = userToken;
            this.name = name;
            this.email = email;
        }

        public static UserDetail newInstance(User user) {
            return UserDetail.builder()
                    .name(user.getName())
                    .email(user.getEmail())
                    .userToken(user.getUserToken())
                    .build();
        }
    }
}
