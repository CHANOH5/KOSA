package com.my.board.dto;

import java.util.Date;
import java.util.List;

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
public class Board {
	// 게시글 상세, 게시글 목록 용도

	private Integer boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardId;
	private Date boardDt;
	
	// 게시글 하나에 여러개의 답글들이 올 수 있기 때문에
	private List<Reply> replies; // 답글 목록
	private Integer replycnt; // 답글 갯수
	
} // end class
