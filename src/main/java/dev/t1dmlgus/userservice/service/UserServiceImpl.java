package dev.t1dmlgus.userservice.service;

import dev.t1dmlgus.userservice.domain.User;
import dev.t1dmlgus.userservice.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserInfo.UserToken join(UserCommand.joinUser userCommand) {

        User user = userCommand.toUser();
        User save = userRepository.save(user);
        return UserInfo.UserToken.newInstance(save);
    }


}
