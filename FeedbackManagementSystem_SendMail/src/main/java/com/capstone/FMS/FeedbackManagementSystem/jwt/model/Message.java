package com.capstone.FMS.FeedbackManagementSystem.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
public class Message {
	
	private String content;
	public Message(String content) {
		this.content=content;
	}

}
