package com.my.board.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
//@Setter
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
	private Long replyNo;

	@Column(name="reply_board_no")
	private Long replyBoardNo;

	@Column(name = "reply_parent_no")
	private Long replyParentNo;
	
	@Column(name="reply_content")
	private String replyContent;
	
	@Column(name="reply_id")
	private String replyId;
	
	@Column(name="reply_dt")
	@ColumnDefault(value="SYSDATE")
	private Date replyDt;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="reply_parent_no")
	private List<ReplyEntity> replyList;
	
//	@Column(name="level")
//	private Integer level;
	
	public void modifyContent(String content) {
		this.replyContent=content;
	}

}


