package model.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.User;
import model.dao.UserDAO;
import model.dao.CommDao;
import model.Community;

public class UserManager {
	private static UserManager userMan = new UserManager();
	private UserDAO userDAO;
	private CommDao commDAO;
	private UserAnalysis userAanlysis;

	private UserManager() {
		try {
			userDAO = new UserDAO();
			commDAO = new CommDao();
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
	
	/*커뮤니티 코드*/
	public Community createCommunity(Community comm) throws SQLException {
		return commDAO.create(comm);		
	}
	
	public List<Community> findCommunityPostList() throws SQLException {
		return commDAO.findCommunityPostList();
	}
	public Community findPostById(int CMPOSTID) throws SQLException {
		return commDAO.findPostById(CMPOSTID);		
	}
	
	
//	public List<User> findCommunityMembers(int commId) throws SQLException {
//		return userDAO.findUsersInCommunity(commId);
//	}
	
	public UserDAO getUserDAO() {
		return this.userDAO;
	}
}