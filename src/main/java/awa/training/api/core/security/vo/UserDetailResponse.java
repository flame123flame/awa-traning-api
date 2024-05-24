package awa.training.api.core.security.vo;

import lombok.Data;

@Data
public class UserDetailResponse {
	private final String username;
	private String nameThPreflix;
	private String nameEnPreflix;
	private String firstNameTh;
	private String lastNameTh;
	private String firstNameEn;
	private String lastNameEn;

}
