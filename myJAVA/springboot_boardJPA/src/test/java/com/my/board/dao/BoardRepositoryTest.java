//package com.my.board.dao;
//
//import java.util.List;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.my.board.dto.Board;
//import com.my.board.dto.Reply;
//import com.my.exception.AddException;
//import com.my.exception.FindException;
//import com.my.exception.ModifyException;
//import com.my.exception.RemoveException;
//
//@SpringBootTest
//class BoardRepositoryTest {
//
//	@Autowired
//	BoardOracleRepository repository;
//	
//	@Test
//	@DisplayName("testSelectAll() test")
//	void testSelectAll() throws FindException {
//		
//		List<Board> list = repository.selectAll();
//		int exepectedSize = 3;
//		
//		Assertions.assertEquals(exepectedSize, list.size());
//		
//	} // testSelectAll
//	
//	@Test
//	@DisplayName("testSelectByBoardNo() - 게시글 번호 1번의 상세조회")
//	void testSelectByBoardNo() throws FindException {
//		int boardNo = 1;
//		Board board = repository.selectByBoardNo(boardNo);
//		
//		String expectedTitle = "제목1";
//		int expectedReplySize = 4;
//		
//		Assertions.assertEquals(expectedTitle, board.getBoardTitle());
//		Assertions.assertEquals(expectedReplySize, board.getReplies().size());
//	} //testSelectByBoardNo
//	
//	@Test
//	@DisplayName("testSelectByBoardNo() - 존재하지 않는 게시글번호로 상세조회")
//	void testSelectByBoardNo1() throws FindException {
//		int boardNo = 0;
//		Board board = repository.selectByBoardNo(boardNo);
//		
//		Assertions.assertNull(board);
//
//		
//		Assertions.assertThrows(FindException.class, ()->{
//			repository.selectByBoardNoOptional(boardNo);
//		});
//		
//	} //testSelectByBoardNo
//	
//	@Test
//	@DisplayName("testinsert()")
//	@Transactional
//	void testinsert() throws AddException {
//		
//		Board board = new Board();
//		
//		board.setBoardTitle("테스트제목3");
//		board.setBoardContent("테스트내용3");
//		board.setBoardId("C");
//		
//		try {
//			repository.insert(board);
//			System.out.println("게시물 등록 완료");
//		} catch (AddException e) {
//			System.out.println("게시물 등록 실패");
//		} // try-catch
//
//	}// testinsert
//	
//	@Test
//	@DisplayName("testupdate()")
//	void testupdate() throws ModifyException {
//		
//		Board board = new Board();
//		
//		board.setBoardNo(5);
//		board.setBoardContent("수정테스트 해볼겐요~");
//		
//		try {
//			repository.update(board);
//			System.out.println("게시물 수정 완료");
//		} catch (ModifyException e) {
//			System.out.println("게시물 수정 실패");
//		} // try-catch
//
//	}// testupdate
//	
//	@Test
//	@DisplayName("testdelete()")
//	void testdelete() throws RemoveException {
//
//		Integer boardNo = 6;
//		
//		try {
//			repository.delete(boardNo);
//			System.out.println("게시물 수정 완료");
//		} catch (RemoveException e) {
//			System.out.println("게시물 수정 실패");
//		} // try-catch
//
//	}// testdelete
//	
//	// ==================== 답글 ========================
//	
//	@Test
//	@DisplayName("testinsertReply()")
//	@Transactional
//	void testinsertReply() throws AddException {
//
//		Board board = new Board();
//		Reply reply = new Reply();
//
//		board.setBoardNo(1); 
//		
//		Integer boardNo = board.getBoardNo();
//		
//		// 답글
//		reply.setReplyParentNo(null);
//		reply.setReplyContent("답글테스트~~~ㅎㅎ1");
//		reply.setReplyId("C");
//		
//		// 답글의 답글
////		reply.setReplyBoardNo(7);
////		reply.setReplyParentNo(1);
////		reply.setReplyContent("답글테스트~~~1");
////		reply.setReplyId("C");
//		
//		try {
//			repository.insertReply(boardNo, reply);
//			System.out.println("답글 등록 완료");
//		} catch (AddException e) {
//			System.out.println("답글 등록 실패");
//		} // try-catch
//
//	}// testinsertReply
//	
//	@Test
//	@DisplayName("testupdateReply()")
//	@Transactional
//	void testupdateReply() throws ModifyException {
//		
//		Reply reply = new Reply();
//		
//		reply.setReplyNo(6);
//		reply.setReplyContent("답글 수정테스트 해볼겐요~");
//		
//		try {
//			repository.updateReply(reply);
//			System.out.println("답글 수정 완료");
//		} catch (ModifyException e) {
//			System.out.println("답글 수정 실패");
//		} // try-catch
//
//	}// testupdateReply
//	
//	@Test
//	@DisplayName("testdeleteReply()")
//	@Transactional
//	void testdeleteReply() throws RemoveException {
//
//		Integer replyNo = 14;
//		
//		// null이면 예외 발생
//		Assertions.assertNotNull(replyNo);
//
//		// 예외가 발생하지 않았다면 코드는 여전히 실행되지만,
//		// 테스트 결과에서 실패로 표시됩니다.
//		Assertions.assertThrows(RemoveException.class, ()->{
//			repository.deleteReply(replyNo);
//		});
//		
//		// 예외발생하면 console에서 뜨고 junit테스트는 성공!
//	}// testdeleteReply
//	
//
//} // end class
