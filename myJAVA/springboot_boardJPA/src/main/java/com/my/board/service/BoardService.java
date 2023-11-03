package com.my.board.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.board.dao.BoardRepository;
import com.my.board.dao.ReplyRepository;
import com.my.board.dto.Board;
import com.my.board.dto.Reply;
import com.my.board.entity.BoardEntity;
import com.my.board.entity.ReplyEntity;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;

@Service
public class BoardService {

	@Autowired
	private BoardRepository repository;
	
	@Autowired
	private ReplyRepository repositoryR;
	
	public List<BoardEntity> findAll() throws FindException {

		return repository.findAll();
		
	} // findAll
	
	public Board findByBoardNo(int boardNo) throws FindException {

		Optional<BoardEntity> optS = repository.findById(boardNo);

		return VoToDTO(optS);
//		return optS.get();
		
	}
	
	public void write(Board board) throws AddException {
//		BoardEntity b = BoardEntity.builder().boardTitle(board.getBoardTitle()).boardContent(board.getBoardContent()).boardId(board.getBoardId()).build();
		BoardEntity b = DtoToVo(board);
		
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

	// 답글 작성
	public void writeReply(Reply reply) throws AddException {

//		ReplyEntity r = ReplyEntity.builder()
//						.replyBoardNo(boardNo)
//						.replyParentNo(reply.getReplyParentNo())
//						.replyContent(reply.getReplyContent())
//						.replyId(reply.getReplyId()).build();
		
		ReplyEntity r = Reply_DtoToVo(reply);
		
		repositoryR.save(r);

	} // writeReply
	
	// 답글 수정
	public void modifyReply(Reply reply) throws ModifyException {
		
		Optional<ReplyEntity> optS = repositoryR.findById(reply.getReplyNo());
		
		ReplyEntity r = optS.get();
		
		r.modifyContent(reply.getReplyContent());
		
		repositoryR.save(r);
		
	} // modifyReply
	
	// 답글 삭제
	public void deleteReply(Long replyNo) throws RemoveException {
		
		repositoryR.deleteById(replyNo);
		
	} // removeReply
	
	
	
	// VO -> DTO
	private Board VoToDTO(Optional<BoardEntity> optS) {
		
		ModelMapper mapper=new ModelMapper();
		
		// 매퍼설정
		mapper.getConfiguration()
		  .setMatchingStrategy(MatchingStrategies.STANDARD)
		  .setFieldAccessLevel(AccessLevel.PRIVATE)
		  .setFieldMatchingEnabled(true);
		
		Object source = optS;
		Class<Board> destinationType=Board.class;
		
		Board dto=mapper.map(source, destinationType);
		dto.setReplycnt(dto.getReplies().size());
		
		return dto;
	} 
	
	// DTO -> VO
	private BoardEntity DtoToVo(Board board) {
		
		ModelMapper mapper=new ModelMapper();
		
		// 매퍼설정
		mapper.getConfiguration()
		  .setMatchingStrategy(MatchingStrategies.STANDARD)
		  .setFieldAccessLevel(AccessLevel.PRIVATE)
		  .setFieldMatchingEnabled(true);
		
		Object source = board;
		
		Class<BoardEntity> destinationType=BoardEntity.class;
		
		BoardEntity entity = mapper.map(source, destinationType);
		
		return entity;
	}
	
	// 댓글 DTO -> VO
	private ReplyEntity Reply_DtoToVo(Reply reply) {
		
		ModelMapper mapper=new ModelMapper();
		
		// 매퍼설정
		mapper.getConfiguration()
		  .setMatchingStrategy(MatchingStrategies.STANDARD)
		  .setFieldAccessLevel(AccessLevel.PRIVATE)
		  .setFieldMatchingEnabled(true);
		
		Object source = reply;
		Class<ReplyEntity> destinationType = ReplyEntity.class;
		
		ReplyEntity entity = mapper.map(source, destinationType);
		
		return entity;
	}
	
} // end class
