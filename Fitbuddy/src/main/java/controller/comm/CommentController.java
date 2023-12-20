package controller.comm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Comment;
import model.dao.CommentDao;
import model.User;
import model.service.UserManager;

public class CommentController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User loggedInUser = (User) session.getAttribute("loggedInUser");

            if (loggedInUser != null) {
                try {
                    int cmPostId = Integer.parseInt(request.getParameter("cmPostId"));
                    int cmUserId = loggedInUser.getUserId();
                    System.out.println("세션에서 가져온 로그인 사용자 ID: " + cmUserId);

                    UserManager manager = UserManager.getInstance();
                    User user = manager.getUserById(cmUserId);

                    Comment comment = new Comment();
                    comment.setCmPostId(cmPostId);
                    comment.setCmUserId(cmUserId);
                    comment.setContent(request.getParameter("content"));
                    comment.setUserName(user.getNickname());
                    comment.setUserProfile(user.getPhoto());

                    CommentDao commentDao = new CommentDao();
                    Comment createdComment = commentDao.create(comment);

                    // 코멘트 생성 로그 추가
                    System.out.println("생성 코멘트: " + createdComment);

                    // 리다이렉트 전 코멘트 ID 로그 추가
                    System.out.println("리다이렉트 전 코멘트 ID: " + cmPostId);

                    return "redirect:/community/view/" + cmPostId;
                } catch (Exception e) {
                    e.printStackTrace();
                    return "/error.jsp";
                }
            }
        }
        return "redirect:/user/login";
    }
}
