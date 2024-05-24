package awa.training.api.core.security.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.UtilityClass;


@UtilityClass
public class JwtResponse {

    @Getter
    @Setter
    public static class Response {
        private String token;
        private String username;
        private String fullName;
        private String nickName;
    }

    @Getter
    @Setter
    public static class Version {
        private String version;
    }


}
