package com.my.board.dao;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.my.board.dto.Board;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;

@SpringBootTest
class BoardRepositoryTest {

	@Autowired
	BoardOracleRepository repository;
	
	@Test
	@DisplayName("testSelectAll() test")
	void testSelectAll() throws FindException {
		
		List<Board> list = repository.selectAll();
		int exepectedSize = 3;
		
		Assertions.assertEquals(exepectedSize, list.size());
		
	} // testSelectAll
	
	@Test
	@DisplayName("testSelectByBoardNo() - 게시글 번호 1번의 상세조회")
	void testSelectByBoardNo() throws FindException {
		int boardNo = 1;
		Board board = repository.selectByBoardNo(boardNo);
		
		String expectedTitle = "제목1";
		int expectedReplySize = 4;
		
		Assertions.assertEquals(expectedTitle, board.getBoardTitle());
		Assertions.assertEquals(expectedReplySize, board.getReplies().size());
	} //testSelectByBoardNo
	
	@Test
	@DisplayName("testSelectByBoardNo() - 존재하지 않는 게시글번호로 상세조회")
	void testSelectByBoardNo1() throws FindException {
		int boardNo = 0;
		Board board = repository.selectByBoardNo(boardNo);
		
		Assertions.assertNull(board);

		
		Assertions.assertThrows(FindException.class, ()->{
			repository.selectByBoardNoOptional(boardNo);
		});
		
	} //testSelectByBoardNo
	
	@Test
	@DisplayName("testinsert()")
	void testinsert() throws AddException {
		
		Board board = new Board();
		
		board.setBoardTitle("테스트제목1");
		board.setBoardContent("테스트내용1");
		board.setBoardId("C");
		
		try {
			repository.insert(board);
			System.out.println("게시물 등록 완료");
		} catch (AddException e) {
			System.out.println("게시물 등록 실패");
		} // try-catch

	}// testinsert
	
//	@Test
//	@DisplayName("testinsertOptional()")
//	void testInsertOptional() throws AddException {
//		
//		Board data =
//		
//
//		Board board = repository.insert(board);
//		
//		
//		Assertions.assertNotNull(board);
//		
//		Assertions.assertThrows(FindException.class, ()->{
//			repository.insertOptional(board);
//		});
//		
//	}
	
	@Test
	@DisplayName("testupdate()")
	void testupdate() throws ModifyException {
		
		Board board = new Board();
		
		board.setBoardNo(5);
		board.setBoardContent("수정테스트 해볼겐요~");
		
		try {
			repository.update(board);
			System.out.println("게시물 수정 완료");
		} catch (ModifyException e) {
			System.out.println("게시물 수정 실패");
		} // try-catch

	}// testupdate
	
	@Test
	@DisplayName("testdelete()")
	void testdelete() throws RemoveException {

		Integer boardNo = 6;
		
		try {
			repository.delete(boardNo);
			System.out.println("게시물 수정 완료");
		} catch (RemoveException e) {
			System.out.println("게시물 수정 실패");
		} // try-catch

	}// testdelete

} // end class
