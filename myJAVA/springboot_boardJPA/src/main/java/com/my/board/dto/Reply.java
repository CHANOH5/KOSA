package com.my.board.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Reply {
	// 계층형 쿼리로 처리 할 것임
	
	private Integer replyNo;
	private Integer replyBoardNo;
	private Integer replyParentNo;
	private String replyContent;
	private String replyId;
	private Date ReplyDt;
	private Integer level;
	
	
	
} // end class
