package awa.training.api.feature.users.dto;

import lombok.Getter;
import lombok.Setter;

public class UsersDTO {

    @Getter
    @Setter
    static public class RegisterReq {
        private String username;
        private String password;
        private String fullName;
        private String nickName;
        private String avatar;
    }


}
