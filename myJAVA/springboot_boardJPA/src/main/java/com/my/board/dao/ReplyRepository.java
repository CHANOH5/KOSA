package com.my.board.dao;

import org.springframework.data.repository.CrudRepository;

import com.my.board.entity.ReplyEntity;

public interface ReplyRepository extends CrudRepository<ReplyEntity, Integer> {

}
