package controller.todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.todo.CreateTodoController;
import controller.user.UserSessionUtils;
import model.dao.TodoCommentDao;
import model.dto.TodoCommentDTO;
import model.dto.TodoDTO;
import model.User;
import model.service.UserManager;

public class CreateTodoCommController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(CreateTodoCommController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("CreateTodoController executed.");
		HttpSession session = request.getSession(false);
		
		//Object postIdObject = session.getAttribute("todopostId");

		if (session != null) {
			User loggedInUser = (User) session.getAttribute("loggedInUser");
			Integer todopostId = (Integer) session.getAttribute("todopostId");
			
			log.debug("todopostId from session (CreateTodoCommController - before setting): {}", todopostId);

			if (loggedInUser != null) {
				try {
					
					int postId;
					if (todopostId != null) {
						postId = todopostId;
					} else {
						postId = Integer.parseInt(request.getParameter("postId"));
					}
					
					
					
					//int postId = (postIdObject != null) ? Integer.parseInt(postIdObject.toString()) : 6;
					int userId = loggedInUser.getUserId();

					log.debug("User ID from session: {}", userId);
					log.debug("Post ID from session: {}", postId);
					//log.debug("TodoPostId from session: {}", todopostId);

					UserManager manager = UserManager.getInstance();
					User user = manager.getUserById(postId);

					TodoCommentDTO todocomm = new TodoCommentDTO();

					todocomm.setTodopostId(postId);
					todocomm.setContent(request.getParameter("content"));
					todocomm.setUserId(userId);
					todocomm.setTodoCheck(0);

					TodoCommentDao todocommentDao = new TodoCommentDao();
					TodoCommentDTO createdComment = todocommentDao.create(todocomm);

					// 코멘트 생성 로그 추가
                    log.debug("Created Comment: {}", createdComment);

                    // 리다이렉트 전 코멘트 ID 로그 추가
                    log.debug("Redirecting to comment ID: {}", postId);

                    //return "/todo/todoPost.jsp";
                    return "redirect:/todo/todolist/comm?userId=" + loggedInUser.getUserId() + "&postId=" + todopostId;

				} catch (Exception e) {
					e.printStackTrace();
					//return "redirect:/todo/todolist/comm?userId=" + loggedInUser.getUserId() + "&postId=" + todopostId;
					return "redirect:/todo/todolist?userId=" + loggedInUser.getUserId();
				}

			}

		}
		return "redirect:/user/login";
	}
}