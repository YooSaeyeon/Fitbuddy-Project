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
import model.User;
import model.service.UserManager;

public class CreateTodoCommController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(CreateTodoCommController.class);
	
	
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	log.debug("CreateTodoController executed.");
    	HttpSession session = request.getSession(false);   
        
        if (session != null) {
        	User loggedInUser = (User) session.getAttribute("loggedInUser");
        	
        	if (loggedInUser != null) {
        		try {
        			
        			int postId = Integer.parseInt(request.getParameter("todopostId"));
        			int userId = loggedInUser.getUserId();
        			
        			log.debug("세션에서 가져온 사용자 ID: {}", userId);
        			
        			UserManager manager = UserManager.getInstance();
        			//User user = manager.getUserById(userId);
        			
        			TodoCommentDTO todocomm = new TodoCommentDTO();
        			todocomm.setTodopostId(postId);
        			todocomm.setContent(request.getParameter("content"));
        			todocomm.setUserId(userId);
        			todocomm.setTodoCheck(0);
        			
        			TodoCommentDao todocommentDao = new TodoCommentDao();
        			TodoCommentDTO createdComment = todocommentDao.create(todocomm);
        			System.out.println("생성 코멘트{}" + createdComment);
        			
        			return "redirect:/todo/todolist/comm?userId=" + userId + "&postId=" + postId;
        			
        		} catch (Exception e) {
        			e.printStackTrace();
        			return "redirect:/todo/todolist?userId=" + loggedInUser.getUserId();
        		}
        		
        		
        		
        	} else {
                // 로그인된 사용자 정보가 없을 때의 처리
                System.out.println("Logged-in user information not found.");
                return "redirect:/user/loginform"; // 예시로 로그인 페이지로 리다이렉트
            }
        } else {
            // 세션이 없을 때의 처리
            System.out.println("Session not found.");
            return "redirect:/user/loginform"; // 예시로 로그인 페이지로 리다이렉트
        }
        
    }
}