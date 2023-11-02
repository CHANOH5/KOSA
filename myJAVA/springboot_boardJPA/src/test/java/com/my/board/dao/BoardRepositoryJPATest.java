package com.my.board.dao;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.my.board.entity.BoardEntity;
import com.my.board.entity.ReplyEntity;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j	
class BoardRepositoryJPATest {
	
	@Autowired
	BoardRepository board;
	
	@Autowired
	ReplyRepository reply;

	@Test
	void insertTest() {
		BoardEntity b = BoardEntity.builder().boardTitle("테스트제목3").boardContent("테스트내용3").boardId("C").build();
		log.error("INSERT용 entitiy 객체 게시글제목: {}, 게시글내용: {}, 작성자: {} ", b.getBoardTitle(), b.getBoardContent(), b.getBoardId());
	
		board.save(b);
	} // insertTest
	
	@Test
	void Update() {
	
		int boardNo = 22;
		Optional<BoardEntity> optS = board.findById(boardNo);
		
		BoardEntity b = optS.get();
		
		b.modifycontent(boardNo, "내용수정2");
		board.save(b);
	}

	@Test
	@Transactional
	@Commit
	void deleteTest() {
		
		int boardNo = 22;
			
		board.deleteById(boardNo);

	} // deleteTest
	
	
	
	
	
	
	
	@Test
	void insertReplyRest() {
		// boardNo 필요
		// parentNo 필요
		// content, id 필요 ,매개변수 boardNo, ReplyEntity

		ReplyEntity r = ReplyEntity.builder().replyContent(null).replyId(null).build();
	} // insertReplyRest

}
