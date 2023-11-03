package com.my.board.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	
	private Long replyNo;
	private Long replyBoardNo;
	private Long replyParentNo;
	private String replyContent;
	@JsonFormat(pattern="yy/MM/dd", timezone="Asia/Seoul")
	private String replyId;
	private Date replyDt;
//	private Integer level;

} // end class
