package com.my.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.board.dao.BoardRepository;
import com.my.board.dto.Board;
import com.my.board.entity.BoardEntity;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;

@Service
public class BoardService {

	@Autowired
	private BoardRepository repository;
	
	public List<BoardEntity> findAll() throws FindException {

		return repository.findAll();
		
	} // findAll
	
	public BoardEntity findByBoardNo(int boardNo) throws FindException {

		Optional<BoardEntity> optS = repository.findById(boardNo);
//
		return optS.get();
		
	}
	
	public void write(Board board) throws AddException {
		BoardEntity b = BoardEntity.builder().boardTitle(board.getBoardTitle()).boardContent(board.getBoardContent()).boardId(board.getBoardId()).build();
		repository.save(b);
	} // insert
	
//	public Integer modify(Board board) throws ModifyException {
	public BoardEntity modify(Board board) throws ModifyException {
		
		int boardNo = board.getBoardNo();
		Optional<BoardEntity> optS = repository.findById(boardNo);
		
		BoardEntity b = optS.get();
		
		b.modifycontent(boardNo, board.getBoardContent());
		
		return repository.save(b);
	} // update
	
	public void remove(Integer boardNo) throws RemoveException {

		repository.deleteById(boardNo);
		
	} // delete
	

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
	
} // end class
