package dev.t1dmlgus.userservice.service;

import dev.t1dmlgus.userservice.domain.User;
import dev.t1dmlgus.userservice.domain.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;


    @Test
    void join_user_return_userToken_start_with_M() {

        // given
        UserCommand.joinUser joinUser =
                UserCommand.joinUser.newInstance("이의현", "dmlgusgngl@gmail.com","1234");
        User user = joinUser.toUser();
        Mockito.when(userRepository.save(any(User.class))).thenReturn(user);

        // when
        UserInfo.UserToken userToken = userService.join(joinUser);

        // then
        Assertions.assertThat(userToken.getUserToken().substring(0, 1)).isEqualTo("M");
    }
}