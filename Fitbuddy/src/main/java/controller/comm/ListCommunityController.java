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
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 세션에서 사용자 ID 가져오기
        HttpSession session = request.getSession();
        Object userId = session.getAttribute("userId");

        // UserManager를 통해 커뮤니티 리스트 가져오기
        UserManager manager = UserManager.getInstance();
        List<Community> commList = manager.findCommunityPostList();

        // commList 객체를 request에 저장하여 커뮤니티 리스트 화면으로 이동(forwarding)
        request.setAttribute("commList", commList);
        return "/comm/comm.jsp";
    }
}
