package dev.t1dmlgus.userservice.service;

/**
 *
 * @Description  유저 서비스 인터페이스
 * @version 1.0, 회원가입
 *
 * @author t1dmlgus
 *
 */

public interface UserService {

    public UserInfo.UserToken join(UserCommand.joinUser userCommand);
}