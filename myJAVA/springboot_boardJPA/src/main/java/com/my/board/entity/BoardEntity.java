package com.my.board.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

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
@DynamicInsert

@Entity
@Table(name="board_tbl")
@SequenceGenerator(name = "board_seq_generator",
				   sequenceName = "board_seq",
				   initialValue = 1,
				   allocationSize = 1
				   )
public class BoardEntity {
	
	// 게시글 상세, 게시글 목록 용도

	@Id
	@Column(name="board_no")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "board_seq_generator")
	private Integer boardNo;
	
	@Column(name="board_title")
	private String boardTitle;
	
	@Column(name="board_content")
//	@NotEmpty(message = "글 내용은 반드시 입력하세요")
//	@Size(max = 10, message = "글 내용은 최대 10자리까지만 가능합니다")
	private String boardContent;
	
	@Column(name="board_id")
//	@NotEmpty(message = "글 작성자 아이디는 반드시 입력하세요")
	private String boardId;
	
	@Column(name="board_dt")
	private Date boardDt;
	
	// 게시글 하나에 여러개의 답글들이 올 수 있기 때문에
//	@OneToMany(cascade = CascadeType.REMOVE,
//			fetch = FetchType.EAGER
//			)
//	@JoinColumn(name="reply_no")
//	private List<ReplyEntity> replies; // 답글 목록
//
//	@Column(name="replycnt")
//	private Integer replycnt; // 답글 갯수
	
	/**
	* 내용수정
	* @param content
	 * @param boardContent 
	*/
	public void modifycontent(int boardNo, String boardContent) {
		this.boardNo = boardNo;
		this.boardContent = boardContent;
	} // modifycontent

}
