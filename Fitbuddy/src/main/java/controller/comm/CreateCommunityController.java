package controller.comm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.Controller;
import model.Community;
import model.service.UserManager;
import model.User;

public class CreateCommunityController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(CreateCommunityController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // "GET" 요청일 때 글 작성 페이지로 이동
        if (request.getMethod().equals("GET")) {
            return "/comm/comm.jsp";
        }

        // 세션에서 현재 로그인한 사용자 정보 확인
        HttpSession session = request.getSession(false);
        if (session != null) {
            User loggedInUser = (User) session.getAttribute("loggedInUser");

            if (loggedInUser != null) {
                // 요청에서 커뮤니티 내용 및 사용자 ID 가져오기
                String content = request.getParameter("content");
//                Object userIdObject = session.getAttribute("userId");

                try {
                    
                    int userId = loggedInUser.getUserId();
                    log.debug("세션에서 가져온 사용자 ID: {}", userId);

                   
                    UserManager manager = UserManager.getInstance();
                    User user = manager.getUserById(userId);

                    Community comm = new Community(userId, content, user.getPhoto(), user.getNickname());
                    manager.createCommunity(comm);

                    log.debug("커뮤니티 생성 완료: {}", comm);


                    return "redirect:/community/commList";
                } catch (Exception e) {
  
                    request.setAttribute("creationFailed", true);
                    request.setAttribute("exception", e);

            
                    return "community/create";
                }
            }
        }


        return "redirect:/user/login"; 
    }
}
