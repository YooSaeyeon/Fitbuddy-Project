package model.dao;

import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Comment;

/*현재 작업중인 폴더*/
public class CommentDao {
	private JDBCUtil jdbcUtil;
	
	public CommentDao() {
		jdbcUtil = new JDBCUtil();
	}
	/*댓글 생성*/
	public Comment create(Comment comment) throws SQLException {
		String sql = "INSERT INTO COMMCOMMENT (CMPOSTID, USERID,USERNAME, CONTENT, USERPROFILE) VALUES (?,?, ?, ?, ?)";
		Object[] param = new Object[] { comment.getCmPostId(), comment.getCmUserId(), comment.getUserName(), comment.getContent(), comment.getUserProfile() };

	    jdbcUtil.setSqlAndParameters(sql, param);

	    try {
	        int affectedRows = jdbcUtil.executeUpdate();
	        if (affectedRows > 0) {
	            ResultSet rs = jdbcUtil.getGeneratedKeys();
	            if (rs != null && rs.next()) {
	                int generatedKey = rs.getInt(1);
	                comment.setCmCommentId(generatedKey);
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
	    String sql = "SELECT CMCOMMENTID, CMPOSTID, USERID, CONTENT, USERNAME,USERPROFILE FROM COMMCOMMENT " +
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
	                    rs.getString("USERNAME"),
	                    rs.getString("USERPROFILE"));
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
