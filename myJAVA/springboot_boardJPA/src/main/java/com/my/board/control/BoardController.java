package com.my.board.control;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.board.dto.Board;
import com.my.board.dto.Reply;
import com.my.board.entity.BoardEntity;
import com.my.board.service.BoardService;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;

@RestController
@RequestMapping("/board")
public class BoardController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BoardService service;
	
	@GetMapping("/list") 
	public List<BoardEntity> list() throws FindException {
		
		return service.findAll();
		
	} // list
	
	@GetMapping("/{boardNo}") // /board/1, /board/2
	public Board info(@PathVariable int boardNo) throws FindException {
		
		return service.findByBoardNo(boardNo);

	} // info
	
	// 글쓰기     /board
	@PostMapping(value="", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> write(@RequestBody Board board) throws AddException {
		
		try {
			service.write(board);

			return new ResponseEntity<>(HttpStatus.OK);			
 		} catch(AddException e) {
 			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);		
 		}
		
	} // write

	// 수정	      /board/1
	@PutMapping(value="/{boardNo}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> modify(@PathVariable int boardNo, @RequestBody Board board) throws ModifyException {
		
		try {
			board.setBoardNo(boardNo);
			service.modify(board);
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (ModifyException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		
	} // modify
	
	// 삭제      /board/1
	@DeleteMapping(value="/{boardNo}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> remove(@PathVariable int boardNo) throws RemoveException {

		try {
			service.remove(boardNo);
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (RemoveException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	} // remove
	

	// 답글쓰기
	@PostMapping(value= {"reply/{boardNo}/{parentNo}", "reply/{boardNo}"})
	public ResponseEntity<?> writeReply(@PathVariable Long boardNo,
										@PathVariable(name="parentNo") Optional<Long> optParentNo,
										@RequestBody Reply reply) throws AddException {
		reply.setReplyBoardNo(boardNo);
		
		service.writeReply(reply);
		if(!optParentNo.isPresent()) { // parentNo가 전달되지 않았을 때 -> 답글
			service.writeReply(reply);
		} else { // parentNo가 전달되었을 때 -> 답글의 답글쓰기
			Long parentNo = optParentNo.get();
			reply.setReplyParentNo(parentNo);
			service.writeReply(reply);
		} // if-else
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	} // writeReply

	// 답글수정
	@PutMapping(value= {"reply/{replyNo}"})
	public ResponseEntity<?> modifyReply(@PathVariable Long replyNo, @RequestBody Reply reply) throws ModifyException {

		try {
			reply.setReplyNo(replyNo);
			service.modifyReply(reply);
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch(ModifyException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} // try-catch
		
	} // modifyReply
	
	// 답글삭제
	@DeleteMapping(value= {"reply/{replyNo}"})
	public ResponseEntity<?> deleteReply(@PathVariable Long replyNo) throws RemoveException {

		
		try {
			
			service.deleteReply(replyNo);
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch(RemoveException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} // try-catch
		
	} // deleteReply
	
} // end class
