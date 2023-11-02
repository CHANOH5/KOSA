package com.my.board.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name="board_reply_tbl")
@SequenceGenerator(name = "board_reply_seq_generator",
				   sequenceName = "board_reply_seq",
				   initialValue = 1,
				   allocationSize = 1
				   )
public class ReplyEntity {

	@Id
	@Column(name="reply_no")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "board_reply_seq_generator")
	private Integer replyNo;
	
	@Column(name="reply_board_no")
	private Integer replyBoardNo;
	
	@JoinColumn(name="board_no")
	private Integer replyParentNo;
	
	@Column(name="reply_content")
	private String replyContent;
	
	@Column(name="reply_id")
	private String replyId;
	
	@Column(name="reply_dt")
	private Date ReplyDt;
	
	@Column(name="level")
	private Integer level;
	
}


