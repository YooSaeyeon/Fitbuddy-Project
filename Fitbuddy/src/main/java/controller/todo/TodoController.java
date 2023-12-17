package controller.todo;

import java.util.ArrayList;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.dao.TodoDao;
import model.dto.TodoDTO;
import model.service.UserManager;

/*public class TodoController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(TodoController.class);

    @Override
    /*public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String uri = request.getRequestURI();
        log.debug("TodoController executing for URI: {}", uri);

        if ("/todo".equals(uri)) {
            return createTodo(request);
        } else if ("/todo/list".equals(uri)) {
            return listTodo(request);
        }

        return null; // or handle as needed
    }

    private String createTodo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object userIdObject = session.getAttribute("userId");
        Object postIdObject = session.getAttribute("todopostId");

        try {
            int userId = (userIdObject != null) ? Integer.parseInt(userIdObject.toString()) : 23;
            int postId = (postIdObject != null) ? Integer.parseInt(postIdObject.toString()) : 1;
            log.debug("User ID from session: {}", userId);
            log.debug("Post ID from session: {}", postId);

            String submitButton = request.getParameter("submit_button");
            if ("submit_todo".equals(submitButton)) {
                LocalDateTime now = LocalDateTime.now();
                Date createdAt = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
                TodoDao todoDao = new TodoDao();
                TodoDTO todo = new TodoDTO(userId, createdAt, postId);
                TodoDTO createdTodo = todoDao.create(todo);

                if (createdTodo != null) {
                    ArrayList<TodoDTO> todoList = (ArrayList<TodoDTO>) session.getAttribute("todoList");
                    if (todoList == null) {
                        todoList = new ArrayList<>();
                    }
                    todoList.add(createdTodo);
                    session.setAttribute("todoList", todoList);
                    log.debug("Create Todo : {}", createdTodo);
                    return "redirect:/todomain.jsp";
                } else {
                    request.setAttribute("creationFailed", true);
                    log.debug("Todo creation failed");
                    return "/todo/todomain.jsp";
                }
            } else {
                return "/todo/todomain.jsp";
            }
        } catch (Exception e) {
            request.setAttribute("creationFailed", true);
            request.setAttribute("exception", e);
            log.error("Error during todo creation", e);
            e.printStackTrace();
            return "Todo item created successfully";
        }
    }

}
*/