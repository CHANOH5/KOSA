package com.my.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.board.dao.BoardOracleRepository;
import com.my.board.dto.Board;
import com.my.board.dto.Reply;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;

@Service
public class BoardService {

	@Autowired
	private BoardOracleRepository repository;
	
	public List<Board> findAll() throws FindException {
		return repository.selectAll();
	}
	
	public Board findByBoardNo(int boardNo) throws FindException {
		return repository.selectByBoardNo(boardNo);
	}
	
	public void write(Board board) throws AddException {
		repository.insert(board);
	} // insert
	
	public Integer modify(Board board) throws ModifyException {
		return repository.update(board);
	} // update
	
	public Integer remove(Integer boardNo) throws RemoveException {
		return repository.delete(boardNo);
	} // delete
	
	// 답글 작성
	public void writeReply(Integer boardNo, Reply reply) throws AddException {
		repository.insertReply(boardNo, reply);
	} // writeReply
	
	// 답글 수정
	public void modifyReply(Reply reply) throws ModifyException {
		repository.updateReply(reply);
	} // modifyReply
	
	// 답글 삭제
	public void deleteReply(Integer replyNo) throws RemoveException {
		repository.deleteReply(replyNo);
	} // removeReply
	
} // end class
