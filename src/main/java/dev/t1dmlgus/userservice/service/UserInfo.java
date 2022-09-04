package dev.t1dmlgus.userservice.service;

import dev.t1dmlgus.userservice.domain.User;
import lombok.Builder;
import lombok.Getter;

public class UserInfo {

    @Getter
    public static class UserToken{

        private String userToken;

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
}
