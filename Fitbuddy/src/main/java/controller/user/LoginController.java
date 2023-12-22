package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.UserManager;
import model.User; // User 모델 import

public class LoginController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = request.getParameter("userId");
        String nickname = request.getParameter("nickname");
        String password = request.getParameter("password");

        try {
            UserManager manager = UserManager.getInstance();
            boolean loginSuccess = manager.login(request, userId, nickname, password); // 수정된 부분

            if (loginSuccess) {
                // 세션에 사용자 정보 저장
                HttpSession session = request.getSession();
                User loggedInUser = manager.findUser(userId, nickname); // 사용자 정보 가져오기
                session.setAttribute("loggedInUser", loggedInUser);
                session.setAttribute("userId", userId);

                // 로그인 성공 시 "/"로 리다이렉트
                return "redirect:/main/main";
            } else {
                request.setAttribute("loginFailed", true);
                return "/user/loginForm.jsp";
            }
        } catch (Exception e) {
            request.setAttribute("loginFailed", true);
            request.setAttribute("exception", e);
            return "redirect:/user/loginform";
        }
    }
}