package controller.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.User;
import model.service.MyPageManager;
import model.Community;
import java.util.List;
import model.Comment;


public class ProfileController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);

        if (session != null) {
            User loggedInUser = (User) session.getAttribute("loggedInUser");

            if (loggedInUser != null) {
                MyPageManager myPageManager = new MyPageManager();
                
                // 사용자 정보 가져오기
                int userId = loggedInUser.getUserId();
                User userProfile = myPageManager.getUserById(userId);

                // 사용자가 작성한 글/댓글 목록 가져오기
                List<Community> userCommList = myPageManager.getUserPosts(userId);
                List<Comment> userCommentList = myPageManager.getUserComments(userId);
                
                // 로그 추가
                System.out.println("Logged in user ID: " + loggedInUser.getUserId());
                System.out.println("User profile: " + userProfile);
                System.out.println("User community list size: " + userCommList.size());
             // 에러 발생 줄을 수정
                if (userCommentList != null) {
                    System.out.println("User comment list size: " + userCommentList.size());
                } else {
                    System.out.println("User comment list is null");
                }
                
                request.setAttribute("loggedInUser", loggedInUser);
                request.setAttribute("userProfile", userProfile);
                request.setAttribute("userCommList", userCommList);
                request.setAttribute("userCommentList", userCommentList);
                
                return "/mypage/profile.jsp"; // 사용자 프로필과 글 목록을 표시하는 JSP 페이지
            }
        } else {
            System.out.println("Session not found.");
            // 로그인 페이지로 리다이렉트 또는 에러 처리
        }

        return "/user/loginForm.jsp"; // 로그인 페이지로 리다이렉트
    }
}