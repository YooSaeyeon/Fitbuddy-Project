package controller.comm;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Community;
import model.service.UserManager;

public class ListCommunityController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	// 커뮤니티 게시글 목록
    	UserManager manager = UserManager.getInstance();
		List<Community> commList = manager.findCommunityPostList();
		
		request.setAttribute("commList", commList);				
		return "/comm/commList.jsp";       

    }
}
