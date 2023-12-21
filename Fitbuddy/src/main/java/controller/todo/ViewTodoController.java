package controller.todo;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.Controller;
import model.dto.TodoDTO;
import model.dao.TodoDao;
import model.dto.TodoCommentDTO;
import model.dao.TodoCommentDao;
import model.service.UserManager;
import model.User;

public class ViewTodoController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(ViewTodoController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        TodoDTO loggedInPost = (TodoDTO) session.getAttribute("loggedInPost");

        if (loggedInUser != null) {
            int userId = loggedInUser.getUserId();

            log.debug("User ID from session: {}", userId);

            try {
                UserManager manager = UserManager.getInstance();

                // loggedInPost가 null이 아니면 해당 Todo를 가져오고, null이면 새로운 Todo 생성
                int postId = (loggedInPost != null) ? loggedInPost.getTodopostId() : 6;

               
                
                /*댓글*/
                TodoCommentDao todocommentDao = new TodoCommentDao();
                
                List<TodoCommentDTO> commentList = todocommentDao.findTodoCommList(postId);
                request.setAttribute("commentList", commentList);

                return "/todo/todoPost.jsp";

            } catch (Exception e) {
                log.error("Exception while processing ViewTodoController", e);
                return "redirect:/todo/todolist?userId=" + loggedInUser.getUserId();
            }
        } else {
            // 로그인된 사용자 정보가 없을 때의 처리
            System.out.println("Logged-in user information not found.");
            return "redirect:/user/loginform"; // 예시로 로그인 페이지로 리다이렉트
        }
        
        
    }
}
