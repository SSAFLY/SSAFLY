package com.ssafy.vo;

import lombok.NoArgsConstructor;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter @Setter @ToString
public class Problem {
	private String key;
	private String title;
	private String answer;
	private String difficulty;
	private String type;
	private String image;
	private ArrayList<String> keywords;
	private int num;
	private String userAnswer;
}
