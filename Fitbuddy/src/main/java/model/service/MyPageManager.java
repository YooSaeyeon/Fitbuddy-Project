package model.service;

import java.util.List;
import model.Community;
import model.User;
import model.dao.MypageDAO;

public class MyPageManager {

    private MypageDAO mypageDAO;

    public MyPageManager() {
        this.mypageDAO = new MypageDAO();
    }

    public List<Community> getUserPosts(int userId) {
        try {
            return mypageDAO.getUserPosts(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getUserById(int userId) {
    	 try {
             User user = mypageDAO.getUserById(userId);

             if (user != null) {
                 System.out.println("User ID in MyPageManager: " + user.getUserId());
             } else {
                 System.out.println("User is null in MyPageManager");
             }

             return user;
         } catch (Exception e) {
             e.printStackTrace();
             return null;
         }
    }
    
    // 다른 필요한 메서드들을 추가로 구현해야 함
}