package awa.training.api.core.security.vo;

import lombok.Data;

@Data
public class JwtRequest {
	private String username;
	private String password;
}
