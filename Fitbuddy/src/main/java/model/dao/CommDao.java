package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Community;
import model.User;

/**
 * 사용자 관리를 위해 데이터베이스 작업을 전담하는 DAO 클래스
 * Community 테이블에서 커뮤니티 정보를 추가, 수정, 삭제, 검색 수행 
 */
public class CommDao {
	 private JDBCUtil jdbcUtil;

	
	public CommDao() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
		
	/**
	 * 커뮤니티 테이블에 새로운 행 생성 (PK 값은 Sequence를 이용하여 자동 생성)
	 */
	public Community create(Community comm) throws SQLException {
		String sql = "INSERT INTO COMMWRITE (USERID, CMPOSTID, CONTENT, COMMDATE, USERPROFILE, USERNAME) VALUES (?, cmpostId_seq.nextval, ?, SYSDATE, ?, ?)";
		Object[] param = new Object[] { comm.getCmUserId(), comm.getContent(), comm.getUserProfile(), comm.getUserName() };

	    jdbcUtil.setSqlAndParameters(sql, param);

	    try {
	        int affectedRows = jdbcUtil.executeUpdate();
	        if (affectedRows > 0) {
	            // 생성된 키 가져오기
	            ResultSet rs = jdbcUtil.getGeneratedKeys();
	            if (rs != null && rs.next()) {
	                int generatedKey = rs.getInt(1);
	                comm.setCmPostId(generatedKey);
	                return comm;
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



	
	/** 
	 * 주어진 ID에 해당하는 커뮤니티 정보를 삭제.
	 */
	public int remove(String commId) throws SQLException {
		String sql = "DELETE FROM Community WHERE cmPostId=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {commId});	// JDBCUtil에 delete문과 매개 변수 설정

		try {				
			int result = jdbcUtil.executeUpdate();	// delete 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}

	
	
	/*커뮤니티 글 목록 조회 부분*/
	public List<Community> findCommunityPostList() throws SQLException {
		   JDBCUtil jdbcUtil = new JDBCUtil();
	       String sql = "SELECT CMPOSTID, USERID, CONTENT, USERPROFILE, COMMDATE, USERNAME FROM COMMWRITE " +
	                    "ORDER BY CMPOSTID";
	       jdbcUtil.setSqlAndParameters(sql, null);

	       try {
	           ResultSet rs = jdbcUtil.executeQuery();
	           List<Community> commList = new ArrayList<>();
	           System.out.println("debug")   ;      
	           while (rs.next()) {
	              Community comm = new Community(
	                      rs.getInt("USERID"),
	                      rs.getInt("CMPOSTID"),
	                      rs.getString("CONTENT"),
//	                      rs.getString("IMG"),
	                      rs.getDate("COMMDATE"),
	                      rs.getString("USERPROFILE"),
	                      rs.getString("USERNAME"));
	               commList.add(comm);
	           }

	           return commList;

	       } catch (Exception ex) {
	           ex.printStackTrace();
	       } finally {
	           jdbcUtil.close();
	       }

	       return null;
	   }

	/*커뮤니티 글 상세 조회 부분*/
	public Community findPostById(int CMPOSTID) throws SQLException {
	    JDBCUtil jdbcUtil = new JDBCUtil(); 
	    String sql = "SELECT CMPOSTID, USERID, CONTENT, USERPROFILE, COMMDATE, USERNAME FROM COMMWRITE "
	               + "WHERE CMPOSTID=?";
	    System.out.println("SQL Query: " + sql + ", CMPOSTID: " + CMPOSTID);
	    jdbcUtil.setSqlAndParameters(sql, new Object[]{CMPOSTID});
	    
	    try {
	        ResultSet rs = jdbcUtil.executeQuery();
	        System.out.println("debugcode");  
	        if (rs.next()) {
	            Community comm = new Community(
	                    rs.getInt("USERID"),
	                    rs.getInt("CMPOSTID"),
	                    rs.getString("CONTENT"),
	                    rs.getDate("COMMDATE"),
	                    rs.getString("USERPROFILE"),
	                    rs.getString("USERNAME"));
	            return comm;
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    } finally {
	        jdbcUtil.close();
	    }
	    return null;
	}
	
	  public User getUserById(int userId) {
	        User user = null;
	        String query = "SELECT * FROM buddyUser WHERE userId = ?";

	        Object[] parameters = { userId };

	        jdbcUtil.setSqlAndParameters(query, parameters);

	        try (ResultSet resultSet = jdbcUtil.executeQuery()) {
	            if (resultSet.next()) {
	                user = new User();
	                user.setUserId(resultSet.getInt("userId"));
	                user.setNickname(resultSet.getString("nickname"));
	                user.setPhoto(resultSet.getString("photo"));
	              
	        
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return user;
	    }
	
	
}