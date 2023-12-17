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
import model.service.UserManager;

public class CreateTodoCommController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(CreateTodoController.class);
	
	
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	log.debug("CreateTodoController executed.");
    	HttpSession session = request.getSession();
        Object userIdObject = session.getAttribute("userId");
        Object postIdObject = session.getAttribute("todopostId");
        Object commentIdObject = session.getAttribute("todoCommentId");
        
        try {
        	
            
            // 세션에서 사용자 ID 가져오기
        	int userId = (userIdObject != null) ? Integer.parseInt(userIdObject.toString()) : 23;
            int postId = (postIdObject != null) ? Integer.parseInt(postIdObject.toString()) : 6;
            int commentId = (commentIdObject != null) ? Integer.parseInt(commentIdObject.toString()) : 1;
            log.debug("User ID from session: {}", userId);
            log.debug("Post ID from session: {}", postId);
            log.debug("comment Id from session: {}", commentId);
            

            // UserManager를 통해 커뮤니티 생성
            TodoCommentDao todoCommentDao = new TodoCommentDao();
            
         // 데이터베이스에서 다음 postId 값을 가져오는 메서드 호출
            int nextCommentId = todoCommentDao.getNextCommentId();
            
            String content = request.getParameter("content");
            
         // TodoCommentDTO 생성
            TodoCommentDTO todoComment = new TodoCommentDTO(postId, content, nextCommentId, userId, 0);

            // 댓글 생성
            TodoCommentDTO createdComment = todoCommentDao.create(todoComment);

            // 생성된 댓글을 세션에 저장
            if (createdComment != null) {
                // 댓글 리스트를 세션에서 가져오기
                @SuppressWarnings("unchecked")
                java.util.List<TodoCommentDTO> todoList = (java.util.List<TodoCommentDTO>) session.getAttribute("todoList");

                if (todoList == null) {
                    todoList = new java.util.ArrayList<>();
                }

                // 생성된 댓글 추가
                todoList.add(createdComment);

                // 세션에 댓글 리스트 저장
                session.setAttribute("todoList", todoList);
                log.debug("Create Comment: {}", createdComment);

                // 성공 시 todopost.jsp로 리다이렉트
                return "redirect:/todoPost.jsp";
            } else {
                // 실패 시 입력 폼으로 포워딩
                request.setAttribute("creationFailed", true);
                log.debug("Comment creation failed");
                return "/todo/todoPost.jsp";
            }
        } catch (Exception e) {
        	// 실패 시 입력 폼으로 포워딩
            request.setAttribute("creationFailed", true);
            request.setAttribute("exception", e);
            log.error("Error during comment creation", e);
            e.printStackTrace();
            return "/todo/todoPost.jsp";
        }
    }
}