package com.my.board.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.board.entity.BoardEntity;
import com.my.board.entity.ReplyEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer>{

//	public void saveReply(Integer boardNo, ReplyEntity r);

//	public BoardEntity findA(BoardEntity b);
	
}
