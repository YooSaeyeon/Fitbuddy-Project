package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

import model.dto.TodoCommentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TodoCommentDao {
	private static final Logger log = LoggerFactory.getLogger(TodoCommentDao.class);
	private JDBCUtil jdbcUtil = null; // JDBCUtil 필드 선언

	public TodoCommentDao() { // 생성자
		jdbcUtil = new JDBCUtil();
	}

	
	public TodoCommentDTO create(TodoCommentDTO todo) throws SQLException {

	    // 레코드가 존재하지 않으면, 삽입을 진행하는 SQL 쿼리
	    String insertSql = "INSERT INTO TODOCOMMENT (todopostId, content, todoCommentId, userId, todoCheck) VALUES (?, ?, ?, ?, ?)";
	    int nextCommentId = getNextCommentId();
	    Object[] insertParam = new Object[]{todo.getTodopostId(), todo.getContent(), nextCommentId, todo.getUserId(), todo.getTodoCheck()};

	    log.debug("Insert SQL: {}", insertSql);
        
	    jdbcUtil.setSqlAndParameters(insertSql, insertParam);

	    try {
	    	int affectedRows = jdbcUtil.executeUpdate();
	    	log.debug("Affected Rows: {}", affectedRows);
	        if (affectedRows > 0) {
	           return todo;
	        }
	        
	    } catch (Exception ex) {
	    	jdbcUtil.rollback();
	        log.error("Error during todo creation", ex);
	        ex.printStackTrace();
	    } finally {
	        jdbcUtil.commit();
	        jdbcUtil.close();
	    }

	    return null; // 실패한 경우에는 null을 반환
	}
		
	public int getNextCommentId() throws SQLException {
        String sql = "SELECT todo_comment_Id_seq.nextval FROM dual"; // dual 테이블에서 시퀀스 다음 값을 가져오기 위해 사용
        jdbcUtil.setSqlAndParameters(sql, null);

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            if (rs != null && rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return 0; // 실패한 경우에는 0을 반환하거나 예외 처리를 수행
    }

	/* TODO 글 목록 조회 부분*/
	public List<TodoCommentDTO> findTodoCommList(int todopostId) throws SQLException{
		JDBCUtil jdbcUtil = new JDBCUtil();
	    String sql = "SELECT TODOPOSTID, CONTENT, TODOCOMMENTID, USERID, TODOCHECK FROM TODOCOMMENT " +
	                 "WHERE TODOPOSTID = ? " +
	                 "ORDER BY TODOCOMMENTID";
	    
	    //Object[] parameters = { todopostId };
	    //jdbcUtil.setSqlAndParameters(sql, parameters);
	    
	    jdbcUtil.setSqlAndParameters(sql,  new Object[]{todopostId});
	    
	    
	    try {
	    	ResultSet rs = jdbcUtil.executeQuery();
	    	List<TodoCommentDTO> commentList = new ArrayList<>();
	    	
	    	while (rs.next()) {
	    		TodoCommentDTO comment = new TodoCommentDTO(
	    				rs.getInt("TODOPOSTID"),
	    				rs.getString("CONTENT"),
	    				rs.getInt("TODOCOMMENTID"),
	    				rs.getInt("USERID"),
	    				rs.getInt("TODOCHECK")
	    				);
	    		commentList.add(comment);
	    	}
	    	return commentList;
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    } finally {
	    	jdbcUtil.close();
	    }
	    
	    return null;

	}
	



	public void updateTodoPost(TodoCommentDTO todoPost) {
	
	}

	public void deleteTodo(String postId) {

	}

	public TodoCommentDTO getTodoPostById(String postId) {
		return null;
	}

}