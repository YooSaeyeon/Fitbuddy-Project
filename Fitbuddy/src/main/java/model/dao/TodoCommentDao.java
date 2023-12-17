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
	    Object[] insertParam = new Object[]{todo.getTodopostId(), todo.getContent(), todo.getTodoCommentId(), todo.getUserId(), todo.getTodoCheck()};

	    log.debug("Insert SQL: {}", insertSql);
        
	    jdbcUtil.setSqlAndParameters(insertSql, insertParam);

	    try {
	    	int affectedRows = jdbcUtil.executeUpdate();
	        log.debug("Affected Rows: {}", affectedRows);
	        if (affectedRows > 0) {
	            // 생성된 키를 가져오기
	            ResultSet rs = jdbcUtil.getGeneratedKeys();
	            if (rs != null && rs.next()) {
	                int generatedKey = rs.getInt(1);
	                todo.setTodoCommentId(generatedKey);
	                return todo;  // TodoDTO 반환
	            }
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



	public void updateTodoPost(TodoCommentDTO todoPost) {
	
	}

	public void deleteTodo(String postId) {

	}

	public TodoCommentDTO getTodoPostById(String postId) {
		return null;
	}

}