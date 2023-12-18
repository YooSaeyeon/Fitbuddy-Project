package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Community;


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

	/**
	 * 주어진  ID에 해당하는 커뮤니티 정보를 데이터베이스에서 찾아 Community 도메인 클래스에 
	 * 저장하여 반환.
	 */
	public Community findCommunity(int commId) throws SQLException {
		String sql = "SELECT CMPOSTID, CONTENT,  COMMDATE " +
	              "FROM COMMWRITE c LEFT OUTER JOIN BUDDYUSER u ON c.USERID = u.USERID " +
	              "WHERE CMPOSTID = ?";

		jdbcUtil.setSqlAndParameters(sql, new Object[] {commId});	// JDBCUtil에 query문과 매개 변수 설정
		Community comm = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {						// 학생 정보 발견
				comm = new Community(
				        rs.getInt("USERID"),
				        rs.getInt("CMPOSTID"),
				        rs.getString("CONTENT"),
//				        rs.getString("IMG"),
				        rs.getDate("COMMDATE"),
				        rs.getString("USERPROFILE"),
				        rs.getString("USERNAME"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return comm;
	}

	/**
	 * 전체 커뮤니티 정보를 검색하여 List에 저장 및 반환
	 */
//	public List<Community> findCommunityList() throws SQLException {
//		String sql = "SELECT CMPOSTID, CONTENT, IMG FROM COMMWRITE " +
//	             "ORDER BY CMPOSTID";
//		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
//					
//		try {
//			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
//			List<Community> commList = new ArrayList<Community>();	// Community들의 리스트 생성
//			while (rs.next()) {
//				Community comm = new Community(			// Community 객체를 생성하여 현재 행의 정보를 저장
//						rs.getInt("cmUserId"),
//						rs.getInt("cmPostId"),
//						rs.getString("content"),
//						rs.getString("img"), null);
//				commList.add(comm);				// List에 Community 객체 저장
//			}		
//			return commList;					
//			
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			jdbcUtil.close();		// resource 반환
//		}
//		return null;
//	}
//	public List<Community> findCommunityList() throws SQLException {
//	    String sql = "SELECT CMPOSTID, USERID, CONTENT, IMG, USERPROFILE, COMMDATE, USERNAME FROM COMMWRITE " +
//	                 "ORDER BY CMPOSTID";
//	    jdbcUtil.setSqlAndParameters(sql, null);
//
//	    try {
//	        ResultSet rs = jdbcUtil.executeQuery();
//	        List<Community> commList = new ArrayList<>();
//
//	        while (rs.next()) {
//	        	Community comm = new Community(
//	        	        rs.getInt("USERID"),
//	        	        rs.getInt("CMPOSTID"),
//	        	        rs.getString("CONTENT"),
//	        	        rs.getString("IMG"),
//	        	        rs.getDate("COMMDATE"),
//	        	        rs.getString("USERPROFILE"),
//	        	        rs.getString("USERNAME"));
//	            commList.add(comm);
//	        }
//
//	        return commList;
//
//	    } catch (Exception ex) {
//	        ex.printStackTrace();
//	    } finally {
//	        jdbcUtil.close();
//	    }
//
//	    return null;
//	}
	
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
	
}