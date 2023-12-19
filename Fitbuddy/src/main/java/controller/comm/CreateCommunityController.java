package controller.comm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.Controller;
import model.Community;
import model.service.UserManager;


public class CreateCommunityController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(CreateCommunityController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	 if (request.getMethod().equals("GET")) {

             return "/comm/comm.jsp";
         }
        String content = request.getParameter("content");
//        String img = request.getParameter("img");

        // 세션에서 사용자 ID 가져오기
        HttpSession session = request.getSession();
        Object userIdObject = session.getAttribute("userId");

        try {
            int userId = (userIdObject != null) ? Integer.parseInt(userIdObject.toString()) : 17;
            log.debug("User ID from session: {}", userId);

            // UserManager를 통해 커뮤니티 생성
            UserManager manager = UserManager.getInstance();
            
         // Community 객체를 생성할 때 빈 생성자 대신 생성자를 통해 필요한 값들을 초기화
            Community comm = new Community(userId, content,  null, null);
            manager.createCommunity(comm);

            log.debug("Create Community : {}", comm);

            // 성공 시 커뮤니티 리스트 화면으로 redirect
            return "redirect:/community/commList";
        } catch (Exception e) {
            // 실패 시 입력 form으로 forwarding
            request.setAttribute("creationFailed", true);
            request.setAttribute("exception", e);

            // Comm 객체를 생성하지 않도록 수정
            return "community/create";
        }
    }
}
