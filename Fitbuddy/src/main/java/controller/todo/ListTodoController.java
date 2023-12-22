package controller.todo;

import java.util.ArrayList;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.dao.TodoDao;
import model.dto.TodoDTO;
import model.service.UserManager;
import model.User;

public class ListTodoController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(ListTodoController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("ListTodoController executed.");

		HttpSession session = request.getSession(false);
		Object postIdObject = session.getAttribute("todopostId");

		if (session != null) {
			// 사용자 정보 가져오기
			User loggedInUser = (User) session.getAttribute("loggedInUser");

			if (loggedInUser != null) {
				int userId = loggedInUser.getUserId();
                log.debug("User ID from session: {}", userId);

				try {
					
					int postId = (postIdObject != null) ? Integer.parseInt(postIdObject.toString()) : 6;
                	log.debug("Post ID from session: {}", postId);
                	
                	
                    // TodoDao를 통해 TODO 목록을 조회
                    TodoDao todoDao = new TodoDao();
                    List<TodoDTO> todoList = todoDao.findTodoList(loggedInUser.getUserId());

                    // 조회된 TODO 목록을 request 속성에 저장
                    request.setAttribute("todoList", todoList);

                    // 사용자 ID를 모델에 추가
                    request.setAttribute("userId", loggedInUser.getUserId());

                    System.out.println("User todo list size: " + todoList.size());
                    // "/todo/todolist.jsp" 페이지로 이동
                    return "/todo/todoList.jsp";
                    //return "redirect:/todo/todolist?userId=" + loggedInUser.getUserId();
                } catch (Exception e) {
                    log.error("Error while retrieving TODO list", e);
                    // 에러 페이지로 리다이렉트 또는 에러 메시지를 처리하는 다른 방식으로 수정 가능
                    return "/todo/todomain.jsp";
                }

			} else {
				// 로그인된 사용자 정보가 없을 때의 처리
				log.debug("Logged-in user information not found.");
				return "redirect:/user/loginform";
			}
		} else {
			// 세션이 없을 때의 처리
			log.debug("Session not found.");
			return "redirect:/user/loginform";
		}
	}
}