package dev.t1dmlgus.userservice.domain;


import dev.t1dmlgus.userservice.common.util.AbstractEntity;
import dev.t1dmlgus.userservice.common.util.TokenUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userToken;

    private String name;

    @Column(unique = true)
    private String email;

    private String encryptedPwd;

    @Builder
    private User(String name, String email, String pwd) {
        this.name = name;
        this.email = email;
        encryptedPwd(pwd);
        createUserToken();
    }

    public static User newInstance(String name, String email, String pwd){
        return User.builder()
                .name(name)
                .email(email)
                .pwd(pwd)
                .build();
    }

    private void createUserToken() {
        this.userToken = TokenUtil.generateToken("user");
    }

    private void encryptedPwd(String pwd) {
        // 패스워드 암호화 로직 필요
        this.encryptedPwd = pwd;
    }

}
