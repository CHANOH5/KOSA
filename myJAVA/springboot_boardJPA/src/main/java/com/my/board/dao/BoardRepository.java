package com.my.board.dao;

import org.springframework.data.repository.CrudRepository;

import com.my.board.entity.BoardEntity;

public interface BoardRepository extends CrudRepository<BoardEntity, Integer>{
	
}
