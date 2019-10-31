package com.ssafy.vo;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter @Setter @ToString
public class Member {
	private String key;
	private String id;
	private String password;
	private String name;
	private String nickname;
	private String grade;
}
