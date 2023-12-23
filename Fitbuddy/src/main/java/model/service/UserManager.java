package model.service;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.User;
import model.dao.UserDAO;
import model.dao.CommDao;
import model.dao.TodoDao;
import model.dto.TodoDTO;
import model.dao.TodoCommentDao;
import model.dto.TodoCommentDTO;
import model.Community;

public class UserManager {
	private static UserManager userMan = new UserManager();
	private UserDAO userDAO;
	private CommDao commDAO;
	private TodoDao todoDAO;
	private TodoCommentDao todoCommentDAO;
	private UserAnalysis userAanlysis;
	

	private UserManager() {
		try {
			userDAO = new UserDAO();
			commDAO = new CommDao();
			todoDAO = new TodoDao();
			todoCommentDAO = new TodoCommentDao();
			userAanlysis = new UserAnalysis(userDAO);
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}

	
	public static UserManager getInstance() {
		return userMan;
	}
	
	public int create(User user) throws SQLException, ExistingUserException {
		if (userDAO.existingUser(user.getNickname())) {
	        String errorMessage = user.getNickname() + "는 이미 존재하는 아이디입니다.";
	        System.out.println(errorMessage); // 또는 로깅 라이브러리 사용
	        throw new ExistingUserException(errorMessage);
	    }
	    int result = userDAO.create(user);
	    if (result == 1) {
	        System.out.println("사용자가 성공적으로 생성되었습니다: " + user.getNickname());
	    }
	    return result;
	}
	
	
	
	public boolean login(HttpServletRequest request, String userId, String nickname, String password)
	        throws SQLException, UserNotFoundException, PasswordMismatchException {
	    try {
	        User user = userDAO.findUser(userId, nickname);

	        if (user == null) {
	            throw new UserNotFoundException(nickname + "는 존재하지 않는 아이디입니다.");
	        }

	        if (!user.matchPassword(password)) {
	            throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
	        }

	        HttpSession session = request.getSession();
	        session.setAttribute("loggedInUser", user); // 세션에 사용자 정보 저장
	        System.out.println("User session saved: " + user.getNickname()); // 로그 추가
	        System.out.println("User session saved: " + user.getUserId()); // 로그 추가

	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw e;
	    }
	}


	public User findUser(String userId, String nickname)
			throws SQLException, UserNotFoundException {
			User user = userDAO.findUser(userId, nickname);
			
			if (user == null) {
				throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
			}		
			return user;
		}
	
	public void updateProfilePhoto(int userId, String filename) {
	    // UserDAO를 생성하고 해당 메서드를 호출해 사용자의 프로필 사진을 업데이트합니다.
		UserDAO userDAO = new UserDAO();
		userDAO.updateProfilePhoto(userId, filename);
		System.out.println("프로필 사진 업데이트 완료");
	}
	
	public Community createCommunity(Community comm) throws SQLException {
		return commDAO.create(comm);		
	}
	public List<Community> findCommunityPostList() throws SQLException {
		return commDAO.findCommunityPostList();
	}
	public Community findPostById(int CMPOSTID) throws SQLException {
		return commDAO.findPostById(CMPOSTID);		
	}
	
	public User getUserById(int userId) {
		 try {
           User user = commDAO.getUserById(userId);

           if (user != null) {
               System.out.println("User ID in COMM: " + user.getUserId());
           } else {
               System.out.println("User is null in comm");
           }

           return user;
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
	}
	
	
	
	public TodoDTO createTodo(TodoDTO todo) throws SQLException {
	    return todoDAO.create(todo);    
	}
	
	public TodoDTO findPostById(int todopostId) throws SQLException {
		return todoDAO.findPostById(todopostId);
	}
	
	public TodoCommentDTO createTodo(TodoCommentDTO todo) throws SQLException {
	    return todoCommentDAO.create(todo);    
	}


	public User getUserById(int userId) {
		 try {
          User user = todoCommentDAO.getUserById(userId);

          if (user != null) {
              System.out.println("User ID in COMM: " + user.getUserId());
          } else {
              System.out.println("User is null in comm");
          }

          return user;
      } catch (Exception e) {
          e.printStackTrace();
          return null;
      }
	}
	
	
	public List<Community> findCommunityPostList() throws SQLException {
		return commDAO.findCommunityPostList();
	}

	
	public List<TodoDTO> findTodoList(int userId){
	    try {
	    	return todoDAO.findTodoList(userId);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	return null;
	    }
	}

	
	public List<TodoCommentDTO> findTodoCommList(int userId, int todopostId) {
		try {
			return todoCommentDAO.findTodoCommList(userId, todopostId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	public UserDAO getUserDAO() {
		return this.userDAO;
	}
}