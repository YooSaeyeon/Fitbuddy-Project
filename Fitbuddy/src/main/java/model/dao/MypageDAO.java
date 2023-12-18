package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Community;
import model.User;

public class MypageDAO {

    private JDBCUtil jdbcUtil;

    public MypageDAO() {
        jdbcUtil = new JDBCUtil();
    }

    public List<Community> getUserPosts(int userId) throws SQLException {
        List<Community> userPosts = new ArrayList<>();
        String query = "SELECT CMPOSTID, USERID, CONTENT, USERPROFILE, COMMDATE, USERNAME FROM COMMWRITE " +
                "WHERE USERID = ? ORDER BY CMPOSTID";

        try (Connection conn = jdbcUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, userId);

            List<Community> userCommList = new ArrayList<>();
            try (ResultSet rs = statement.executeQuery()) {
            	 Community comm = new Community(
                         rs.getInt("USERID"),
                         rs.getInt("CMPOSTID"),
                         rs.getString("CONTENT"),
                         rs.getDate("COMMDATE"),
                         rs.getString("USERPROFILE"),
                         rs.getString("USERNAME"));
                 userCommList.add(comm);
                }
            return userCommList;
            }
        }
 

    public User getUserById(int userId) throws SQLException {
        User user = null;
        String query = "SELECT * FROM User WHERE userId = ?";

        try (Connection conn = jdbcUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User();
                    user.setUserId(resultSet.getInt("userId"));
                    user.setNickname(resultSet.getString("nickname"));
                    user.setPhoto(resultSet.getString("photo"));
                    // 나머지 필드들도 설정
                }
            }
        }

        return user;
    }

    // 다른 필요한 메서드들을 추가로 구현해야 함
}