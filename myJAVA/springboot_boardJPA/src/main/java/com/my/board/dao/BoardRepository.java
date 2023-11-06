package com.my.board.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.my.board.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer>{

	// 게시글 상세 검색용 메서드, JPQL사용
//	@Query(native=true, "SELECT *\r\n"
//			+ "		FROM board b LEFT JOIN \r\n"
//			+ "			(SELECT level,r1.* FROM board_reply r1 START WITH reply_parent_no IS NULL CONNECT BY PRIOR reply_no =  reply_parent_no \r\n"
//			+ "			 ORDER SIBLINGS BY reply_no DESC)r\r\n"
//			+ "		ON b.board_no = r.reply_board_no\r\n"
//			+ "		WHERE board_no = #{boardNo}")
//	public List<Object[]> findByBoardNo(int boardNo);
	// 리턴되는 타입이 entity가 아닌 경우 List<Object[]> 배열타입으로 해야 함
	
//	public void saveReply(Integer boardNo, ReplyEntity r);

//	public BoardEntity findA(BoardEntity b);
	
}
