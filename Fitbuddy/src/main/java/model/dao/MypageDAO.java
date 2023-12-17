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
        String query = "SELECT * FROM Community WHERE cmUserId = ?";

        try (Connection conn = jdbcUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Community post = new Community();
                    post.setCmPostId(resultSet.getInt("cmPostId"));
                    post.setContent(resultSet.getString("content"));
                    // 나머지 필드들도 설정

                    userPosts.add(post);
                }
            }
        }

        return userPosts;
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
                    // 나머지 필드들도 설정
                }
            }
        }

        return user;
    }

    // 다른 필요한 메서드들을 추가로 구현해야 함
}