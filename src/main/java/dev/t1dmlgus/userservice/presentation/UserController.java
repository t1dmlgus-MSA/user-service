package dev.t1dmlgus.userservice.presentation;

import dev.t1dmlgus.userservice.common.response.CommonResponse;
import dev.t1dmlgus.userservice.service.UserCommand;
import dev.t1dmlgus.userservice.service.UserInfo;
import dev.t1dmlgus.userservice.service.UserService;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-service")
public class UserController {

    private final Environment env;
    private final UserService userService;

    public UserController(Environment env, UserService userService) {
        this.env = env;
        this.userService = userService;
    }

    @GetMapping("/health-check")
    public ResponseEntity<CommonResponse<String>> status(){

        String healthCheckMessage =
                String.format("Working User-service on PORT %s", env.getProperty("server.port"));
        CommonResponse<String> commonResponse = CommonResponse.of(healthCheckMessage);
        return ResponseEntity.ok(commonResponse);
    }

    @PostMapping("/users")
    public ResponseEntity<CommonResponse<UserInfo.UserToken>> joinUser(
            @RequestBody UserDto.Join userDto){

        UserCommand.joinUser userCommand = userDto.toCommand();
        UserInfo.UserToken userToken = userService.join(userCommand);
        CommonResponse<UserInfo.UserToken> commonResponse = CommonResponse.of(userToken, "회원가입 되었습니다.");
        return ResponseEntity.ok(commonResponse);
    }

    @GetMapping("/users/{userToken}")
    public ResponseEntity<CommonResponse<UserInfo.UserDetail>> inquireUser(
            @PathVariable String userToken){

        UserInfo.UserDetail userInfo = userService.inquire(userToken);
        CommonResponse<UserInfo.UserDetail> commonResponse = CommonResponse.of(userInfo, "회원이 조회되었습니다.");
        return ResponseEntity.ok(commonResponse);
    }
}
