package model.service;

import java.sql.SQLException;
import java.util.List;

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
		if (userDAO.existingUser(user.getNickname()) == true) {
			throw new ExistingUserException(user.getNickname() + "는 존재하는 아이디입니다.");
		}
		return userDAO.create(user);
	}
	
	
	
	public boolean login(String userId, String nickname, String password)
			throws SQLException, UserNotFoundException, PasswordMismatchException {
			
			try {
		        User user = userDAO.findUser(userId, nickname);
		        
		        if (user == null) {
		            throw new UserNotFoundException(nickname + "는 존재하지 않는 아이디입니다.");
		        }

		        if (!user.matchPassword(password)) {
		            throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		        }
		        return true;
		    } catch (Exception e) {
		        e.printStackTrace(); // 또는 로깅 라이브러리 사용
		        throw e; // 예외를 다시 던져서 컨트롤러에서 처리
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