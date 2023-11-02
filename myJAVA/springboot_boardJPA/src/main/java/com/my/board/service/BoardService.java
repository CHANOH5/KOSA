//package com.my.board.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.my.board.dao.BoardRepository;
//import com.my.board.dto.Board;
//import com.my.board.dto.Reply;
//import com.my.exception.AddException;
//import com.my.exception.FindException;
//import com.my.exception.ModifyException;
//import com.my.exception.RemoveException;
//
//@Service
//public class BoardService {
//
//	@Autowired
//	private BoardRepository repository;
//	
//	public List<Board> findAll() throws FindException {
//		return repository.findAll();
//	}
//	
//	public Board findByBoardNo(int boardNo) throws FindException {
//		
//		Optional<> opt = 
//		
//		return repository.findById(boardNo);
//	}
//	
//	public void write(Board board) throws AddException {
//		repository.save(board);
//	} // insert
//	
//	public Integer modify(Board board) throws ModifyException {
//		return repository.save(board);
//	} // update
//	
//	public Integer remove(Integer boardNo) throws RemoveException {
//		return repository.deleteById(boardNo);
//	} // delete
//	
//	// 답글 작성
//	public void writeReply(Integer boardNo, Reply reply) throws AddException {
//		repository.insertReply(boardNo, reply);
//	} // writeReply
//	
//	// 답글 수정
//	public void modifyReply(Reply reply) throws ModifyException {
//		repository.updateReply(reply);
//	} // modifyReply
//	
//	// 답글 삭제
//	public void deleteReply(Integer replyNo) throws RemoveException {
//		repository.deleteReply(replyNo);
//	} // removeReply
//	
//} // end class
