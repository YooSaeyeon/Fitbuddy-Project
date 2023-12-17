package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

import model.dto.TodoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TodoDao {
	private static final Logger log = LoggerFactory.getLogger(TodoDao.class);
	private JDBCUtil jdbcUtil = null; // JDBCUtil 필드 선언

	public TodoDao() { // 생성자
		jdbcUtil = new JDBCUtil();
	}

	
	public TodoDTO create(TodoDTO todo) throws SQLException {
		java.util.Date currentDate = new java.util.Date();
	    java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
		
	 // 1. Check if exists 로그 추가
	    String checkIfExistsSql = "SELECT COUNT(*) FROM TODO WHERE userId = ? AND created_at = TO_DATE(?, 'YYYY-MM-DD')";
        Object[] checkIfExistsParam = new Object[]{todo.getUserId(), sqlDate};
        jdbcUtil.setSqlAndParameters(checkIfExistsSql, checkIfExistsParam);

        log.debug("Check if exists SQL: {}", checkIfExistsSql);

	    try {
	    	ResultSet rs = jdbcUtil.executeQuery();
	        if (rs != null && rs.next()) {
	            int count = rs.getInt(1);
	            if (count > 0) {
	                // 현재 날짜와 사용자 ID로 된 레코드가 이미 존재하면, null을 반환하거나 필요한 처리를 수행
	            	log.debug("Record already exists for userId={} and created_at={}", todo.getUserId(), sqlDate);
	                return null;
	            }
	        }
	    } catch (Exception ex) {
	        jdbcUtil.rollback();
	        log.error("Error checking if record exists", ex);
	        ex.printStackTrace();
	    }

	    // 레코드가 존재하지 않으면, 삽입을 진행하는 SQL 쿼리
	    String insertSql = "INSERT INTO TODO (userId, created_at, todopostId) VALUES (?, ?, ?)";
	    int nextPostId = getNextPostId();
	    Object[] insertParam = new Object[]{todo.getUserId(), sqlDate, nextPostId};

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
	                todo.setTodopostId(generatedKey);
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
	
	public int getNextPostId() throws SQLException {
        String sql = "SELECT todopostId_seq.nextval FROM dual"; // dual 테이블에서 시퀀스 다음 값을 가져오기 위해 사용
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
	public List<TodoDTO> findTodoListByUserId(int userId) throws SQLException {
	    String sql = "SELECT USERID, CREATED_AT, TODOPOSTID FROM TODO " +
	                 "WHERE USERID = ? " +
	                 "ORDER BY CREATED_AT";
	    jdbcUtil.setSqlAndParameters(sql, new Object[]{userId});

	    try {
	        ResultSet rs = jdbcUtil.executeQuery();
	        List<TodoDTO> todoList = new ArrayList<>();

	        while (rs.next()) {
	            TodoDTO todo = new TodoDTO(
	                    rs.getInt("USERID"),
	                    rs.getDate("CREATED_AT"),
	                    rs.getInt("TODOPOSTID")
	            );
	            todoList.add(todo);
	        }

	        return todoList;

	    } catch (Exception ex) {
	        ex.printStackTrace();
	    } finally {
	        jdbcUtil.close();
	    }

	    return null;
	}


	public void updateTodoPost(TodoDTO todoPost) {
	
	}

	public void deleteTodo(String postId) {

	}

	public TodoDTO getTodoPostById(String postId) {
		return null;
	}

}