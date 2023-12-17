package controller.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.User;
import model.service.MyPageManager;

public class ProfileController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 세션에서 현재 로그인한 사용자 정보 읽기
        HttpSession session = request.getSession(false);
        if (session != null) {
            User loggedInUser = (User) session.getAttribute("loggedInUser");
            if (loggedInUser != null) {
                int userId = loggedInUser.getUserId();
                // 여기서 필요한 작업 수행
                MyPageManager myPageManager = new MyPageManager();
                User userProfile = myPageManager.getUserById(userId);

                if (userProfile != null) {
                    request.setAttribute("userProfile", userProfile);
                } else {
                    // 사용자 프로필이 없을 경우 로그로 확인
                    System.out.println("User profile is null for userId: " + userId);
                }
            }
        } else {
            // 세션이 없을 경우 로그로 확인
            System.out.println("Session not found.");
        }

        return "/mypage/profile.jsp";
    }
}
