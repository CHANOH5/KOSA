package com.my.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.board.dto.Board;
import com.my.board.dto.Reply;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;

@Repository
public class BoardOracleRepository {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	/**
	 * 게시글 목록을 검색한다
	 * @return 게시글 목록
	 * @throws FindException
	 */
	public List<Board> selectAll() throws FindException{
		
		SqlSession session = null;
		
		try {
			
			session = sqlSessionFactory.openSession();
			List<Board> list = session.selectList("com.my.board.BoardMapper.selectAll");
			
			return list;
			
		} catch(Exception e) {
			throw new FindException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
		} // try-catch-finally
		
	} // selectAll
	
	/**
	 * 게시글 번호에 대한 답글을 조회한다
	 * @param boardNo 게시글 번호
	 * @return 답글
	 * @throws FindException
	 */
	public Board selectByBoardNo(Integer boardNo) throws FindException {
		
		SqlSession session = null;
		
		try {
			
			session = sqlSessionFactory.openSession();
			
			Board list = session.selectOne("com.my.board.BoardMapper.selectBoardNo", boardNo);
			
			return list;
		} catch(Exception e) {
			throw new FindException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
		} // try-catch-finally
		
	} // selectByBoardNo()
	
	
	public Optional<Board> selectByBoardNoOptional(int boardNo) throws FindException{
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			Board b =  session.selectOne("com.my.board.BoardMapper.selectByBoardNo", boardNo);
			Optional<Board> optB = Optional.of(b);
			return optB;
		}catch(Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		} // try-cathc-finally
	} // selectByBoardNoOptional
	
	public void insert(Board board) throws AddException {
		
		SqlSession session = null;
		
		Map<String, Object> map = new HashMap();
		
		try {
			session = sqlSessionFactory.openSession();
			
			map.put("boardTitle", board.getBoardTitle());
			map.put("boardContent", board.getBoardContent());
			map.put("boardId", board.getBoardId());

			session.insert("com.my.board.BoardMapper.insert", map);

		}catch(Exception e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		} // try-cathc-finally
		
	} // insert
	
	public Optional insertOptional(Board board) throws FindException{
		
		SqlSession session = null;
		
		try {
			
			session = sqlSessionFactory.openSession();
			Board b =  session.selectOne("com.my.board.BoardMapper.insert", board);
			Optional<Board> optB = Optional.of(b);
			return optB;
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		} // try-cathc-finally
	
	}
	
	public Integer update(Board board) throws ModifyException {
		
		SqlSession session = null;
		
		Map<String, Object> map =new HashMap();
		
		try {
			session = sqlSessionFactory.openSession();
			
			map.put("boardNo", board.getBoardNo());
			map.put("boardContent", board.getBoardContent());
			
			session.update("com.my.board.BoardMapper.update", map);

		} catch(Exception e) {
			e.printStackTrace();
			throw new ModifyException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
		} // try-catch-finally
		
		return null;
		
	} // update
	
	public Integer delete(Integer boardNo) throws RemoveException {
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			
			session.delete("com.my.board.BoardMapper.delete", boardNo);

		} catch(Exception e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
		} // try-catch-finally
	
		return null;
	} // delete
	
	// ======================== 답글 =========================
	
	public void insertReply(Integer boardNo, Reply reply) throws AddException {
		
		SqlSession session = null;
		Map<String, Object> map = new HashMap<>();
		
		try {
			session = sqlSessionFactory.openSession();
			
			map.put("replyBoardNo", boardNo);
			map.put("replyParentNo", reply.getReplyParentNo());
			map.put("replyContent", reply.getReplyContent());
			map.put("replyId", reply.getReplyId());
			
			session.delete("com.my.board.BoardMapper.reply", map);

		} catch(Exception e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
		} // try-catch-finally

	} // insertReply
	
	public void updateReply(Reply reply) throws ModifyException {
		
		SqlSession session = null;
		
		Map<String, Object> map =new HashMap();
		
		try {
			session = sqlSessionFactory.openSession();
			
			map.put("replyNo", reply.getReplyNo());
			map.put("replyContent", reply.getReplyContent());
			
			int rowcnt = session.update("com.my.board.BoardMapper.updateReply", map);
			
			if(rowcnt == 0) {
				throw new RemoveException("수정실패: 답글이 없습니다");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new ModifyException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
		} // try-catch-finally
		
	} // updateReply
	
	public void deleteReply(Integer replyNo) throws RemoveException {
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			
			int rowcnt = session.delete("com.my.board.BoardMapper.deleteReply", replyNo);
			
			if(rowcnt == 0) {
				throw new RemoveException("삭제실패: 답글이 없습니다");
			}

		} catch(Exception e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
		} // try-catch-finally
	
	} // deleteReply

} // end class
