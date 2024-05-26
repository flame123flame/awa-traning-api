package awa.training.api.feature.users.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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

    @Getter
    @Setter
    static public class FindAllUserRes {
        private Long id;
        private String username;
        private String fullName;
        private String nickName;
        private String avatar;
    }

    @Getter
    @Setter
    static public class UpdateUserRes {
        private Long id;
        private String username;
        private String password;
        private String fullName;
        private String nickName;
        private String avatar;
        public void setCreated_at(LocalDateTime now) {
        }
    }

    @Getter
    @Setter
    static public class DeleteUserRes {
        private Long id;
    }

}
