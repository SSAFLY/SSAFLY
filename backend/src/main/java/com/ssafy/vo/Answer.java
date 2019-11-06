package com.ssafy.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter @Setter @ToString
public class Answer {
	private String id;
	private String problemKey;
	private String problem;
	private String solution;
	private String answer;
}
