package model.dao;

import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Comment;
import model.User;
import model.service.UserManager;

/*현재 작업중인 폴더*/
public class CommentDao {
	private JDBCUtil jdbcUtil;
	
	public CommentDao() {
		jdbcUtil = new JDBCUtil();
	}
	/*댓글 생성*/
	/*댓글 생성*/
	public Comment create(Comment comment) throws SQLException {
	    String sql = "INSERT INTO COMMCOMMENT (CMPOSTID, USERID,USERNAME, CONTENT, USERPROFILE) VALUES (?,?, ?, ?, ?)";
	    Object[] param = new Object[] {
	            comment.getCmPostId(),
	            comment.getCmUserId(),
	            comment.getUserName() != null ? comment.getUserName() : "Unknown",  // null이면 "Unknown"으로 설정
	            comment.getContent(),
	            comment.getUserProfile()
	    };

	    jdbcUtil.setSqlAndParameters(sql, param);

	    try {
	    	String key[] = {"CMcommentID"};
	        int affectedRows = jdbcUtil.executeUpdate(key);
	        if (affectedRows > 0) {
	            ResultSet rs = jdbcUtil.getGeneratedKeys();
	            if (rs != null && rs.next()) {
	                int generatedKey = rs.getInt(1);
	                comment.setCmCommentId(generatedKey);
	                System.out.println("댓글생성시 USERNAME: " + comment.getUserName());

	                return comment;
	            }
	        }
	    } catch (Exception ex) {
	        jdbcUtil.rollback();
	        ex.printStackTrace();
	    } finally {
	        jdbcUtil.commit();
	        jdbcUtil.close();
	    }

	    return null;
	}

	/*특정 게시글 댓글 목록 조회 부분*/
	public List<Comment> findCommentList(int cmPostId) throws SQLException {
	    JDBCUtil jdbcUtil = new JDBCUtil();
	    String sql = "SELECT CMCOMMENTID, CMPOSTID, USERID, CONTENT,USERPROFILE,USERNAME FROM COMMCOMMENT " +
	                 "WHERE CMPOSTID = ? " +
	                 "ORDER BY CMCOMMENTID";
	    jdbcUtil.setSqlAndParameters(sql, new Object[]{cmPostId});

	    try {
	        ResultSet rs = jdbcUtil.executeQuery();
	        List<Comment> commentList = new ArrayList<>();
	        while (rs.next()) {
	            Comment comment = new Comment(
	                    rs.getInt("CMCOMMENTID"),
	                    rs.getInt("CMPOSTID"),
	                    rs.getInt("USERID"),
	                    rs.getString("CONTENT"),
	                    rs.getString("USERPROFILE"),
	                    rs.getString("USERNAME"));

	            
	            System.out.println("댓글의 USERNAME: " + comment.getUserName());

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

}
