package controller.todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDateTime;
import java.time.ZoneId;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.todo.CreateTodoController;
import model.dao.TodoDao;
import model.dto.TodoDTO;
import model.User;
import model.service.UserManager;

public class CreateTodoController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(CreateTodoController.class);
	
	
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	log.debug("CreateTodoController executed.");
    	HttpSession session = request.getSession(false);
    	Object postIdObject = session.getAttribute("todopostId");
    	
    	if (session != null) {
            User loggedInUser = (User) session.getAttribute("loggedInUser");
            if (loggedInUser != null) {
                int userId = loggedInUser.getUserId();
                log.debug("User ID from session: {}", userId);

                try {
                    // 세션에서 사용자 ID 가져오기
                	// postId 파라미터를 가져올 때 예외 처리 추가
                	
                	int postId = (postIdObject != null) ? Integer.parseInt(postIdObject.toString()) : 6;
                	log.debug("Post ID from session: {}", postId);
                    

                    // TODO 작성+ 클릭 시에만 저장되도록 변경
                    String submitButton = request.getParameter("submit_button");
                    if ("submit_todo".equals(submitButton)) {
                        LocalDateTime now = LocalDateTime.now();
                        Date createdAt = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
                        TodoDao todoDao = new TodoDao();
                        TodoDTO todo = new TodoDTO(userId, createdAt, postId);
                        TodoDTO createdTodo = todoDao.create(todo);

                        // 생성된 TO DO 목록을 어딘가에 저장 (예: 세션에 저장)
                        if (createdTodo != null) {
//                            ArrayList<TodoDTO> todoList = (ArrayList<TodoDTO>) session.getAttribute("todoList");
//                            if (todoList == null) {
//                            	todoList = new ArrayList<TodoDTO>();
//                            }
//                            todoList.add(createdTodo);
//                            session.setAttribute("todoList", todoList);
//                            log.debug("Create Todo : {}", createdTodo);
                        	
                        	// find로 가져와서 리다이렉션하기
//                            
//                            System.out.println("User todo list size: " + todoList.size());
                        	
                        	// TodoDao를 통해 TODO 목록을 조회
                            List<TodoDTO> todoList = todoDao.findTodoList(loggedInUser.getUserId());

                            // 조회된 TODO 목록을 request 속성에 저장
                            request.setAttribute("todoList", todoList);

                            // 사용자 ID를 모델에 추가
                            request.setAttribute("userId", loggedInUser.getUserId());

                            System.out.println("User todo list size: " + todoList.size());
                            // "/todo/todolist.jsp" 페이지로 이동
                            
                         // 성공 시 TodoList 화면으로 redirect
                            return "redirect:/todo/todolist?userId=" + loggedInUser.getUserId();
                        } else {
                            // 실패 시 입력 form으로 forwarding
                            request.setAttribute("creationFailed", true);
                            log.debug("Todo creation failed");
                            return "/todo/todomain.jsp";
                        }
                    } else {
                        // TODO 작성+ 이외의 경우에는 다른 처리 수행 (필요에 따라 추가)
                        return "/todo/todomain.jsp";
                    }
                } catch (Exception e) {
                    // 실패 시 입력 form으로 forwarding
                    request.setAttribute("creationFailed", true);
                    request.setAttribute("exception", e);
                    log.error("할 일 생성 중 오류 발생", e);
                    e.printStackTrace();
                    return "redirect:/todo/todolist?userId=" + loggedInUser.getUserId();
                    //return "/todo/todomain.jsp";
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