package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;
import model.dao.JDBCUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UserDAO {

    private JDBCUtil jdbcUtil;

    public UserDAO() {
        jdbcUtil = new JDBCUtil();
    }

    public int create(User user) throws SQLException {
        String sql = "INSERT INTO BUDDYUSER (NICKNAME, PASSWORD, GENDER, PHOTO) VALUES (?, ?, ?, ?)";
        Object[] param = new Object[] { user.getNickname(), user.getPassword(), user.getGender(), user.getPhoto() };

        jdbcUtil.setSqlAndParameters(sql, param);

        try {
            int result = jdbcUtil.executeUpdate();
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }

        return 0;
    }

	
	/**
	 * 주어진 사용자 ID에 해당하는 사용자 정보를 데이터베이스에서 찾아 User 도메인 클래스에 
	 * 저장하여 반환.
	 */
    public User findUser(String userId, String nickname) throws SQLException {
        String sql = "SELECT nickname, password, userId, gender "
        			+ "FROM buddyUser "
        			+ "WHERE nickname=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {nickname});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {						// 학생 정보 발견
				User user = new User(		// User 객체를 생성하여 학생 정보를 저장
						rs.getString("nickname"),
						rs.getString("password"),
						rs.getInt("userId"),
						rs.getString("gender")
				);
				System.out.println("User found in database: " + user.getNickname());
	            return user;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		
		System.out.println("User not found in database for nickname: " + userId);
		return null;
	}


	
    /**
	 * 주어진 사용자 ID에 해당하는 사용자가 존재하는지 검사 
	 */
	public boolean existingUser(String nickname) throws SQLException {
		String sql = "SELECT count(*) FROM buddyUser WHERE nickname=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {nickname});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return false;
	}
	
	// 로그인 db 확인
		public boolean validateUser(String nickname, String password) throws SQLException {
			String sql = "SELECT count(*) FROM buddyUser WHERE nickname=? AND password=?";
			jdbcUtil.setSqlAndParameters(sql,  new Object[] {nickname, password});
			
			try {
				ResultSet rs = jdbcUtil.executeQuery();
				if (rs.next()) {
					int count = rs.getInt(1);
					System.out.println("User validation result: " + count);
					return (count == 1);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				return false;
			} finally {
				jdbcUtil.close();
			}
			return false;
		}
	

	
	

}