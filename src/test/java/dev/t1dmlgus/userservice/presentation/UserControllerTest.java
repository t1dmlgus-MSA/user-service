package dev.t1dmlgus.userservice.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.t1dmlgus.userservice.domain.User;
import dev.t1dmlgus.userservice.service.UserCommand;
import dev.t1dmlgus.userservice.service.UserInfo;
import dev.t1dmlgus.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(UserController.class)
class UserControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    void setup(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    void user_service_on_status_returns_message_hello_user_service() throws Exception {

        // when
        ResultActions resultActions = mockMvc.perform(
                get("/health-check")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        );

        // then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.message").value("Hello user-service!"))
                .andDo(print()
                );

    }

    @Test
    void join_user_returns_userToken_exists() throws Exception {

        //given
        UserDto.join userDto = UserDto.join.builder()
                .name("이의현")
                .email("dmlgusgngl@gmail.com")
                .pwd("1234")
                .build();

        User user = userDto.toCommand().toUser();
        UserInfo.UserToken userToken = UserInfo.UserToken.newInstance(user);

        String json = new ObjectMapper().writeValueAsString(userDto);
        given(userService.join(any(UserCommand.joinUser.class)))
                .willReturn(userToken);

        //when
        ResultActions resultActions = mockMvc.perform(
                post("/users")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        );

        //then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.message").value("회원가입 되었습니다."))

                // 추후, startsWith와 같은 검증 테스트 필요
                .andExpect(jsonPath("$.data.userToken").exists())
                .andDo(print()
                );

        verify(userService).join(any(UserCommand.joinUser.class));

    }
}