package model.dao;

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

    public List<Community> getUserPosts(int userId) {
        List<Community> userCommList = new ArrayList<>();
        String query = "SELECT CMPOSTID, USERID, CONTENT, USERPROFILE, COMMDATE, USERNAME FROM COMMWRITE " +
                "WHERE USERID = ? ORDER BY CMPOSTID";

        Object[] parameters = { userId };

        jdbcUtil.setSqlAndParameters(query, parameters);

        try (ResultSet rs = jdbcUtil.executeQuery()) {
            while (rs.next()) {
                Community comm = new Community(
                        rs.getInt("USERID"),
                        rs.getInt("CMPOSTID"),
                        rs.getString("CONTENT"),
                        rs.getDate("COMMDATE"),
                        rs.getString("USERPROFILE"),
                        rs.getString("USERNAME"));
                userCommList.add(comm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userCommList;
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
