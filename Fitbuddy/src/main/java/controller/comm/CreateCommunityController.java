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
             // 회원 가입 폼 요청 시 폼으로 이동
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

//        // 추가: 세션에서 userIdObject가 null이 아닌지 확인
//        if (userIdObject != null) {
//            Community comm = null; // comm 객체를 선언하고 초기화
//
//            try {
//                // userIdObject가 null이 아니라면 int로 변환하여 userId에 저장
////                int userId = (int) userIdObject;
//                
//            	int userId = (userIdObject != null) ? Integer.parseInt(userIdObject.toString()) : 1;
//            	log.debug("User ID from session: {}", userId);
//
//                // comm의 재선언 제거
//                comm = new Community(userId, 0, content, img, null, null, null);
//
//                // UserManager를 통해 커뮤니티 생성
//                UserManager manager = UserManager.getInstance();
//                manager.createCommunity(comm);
//
//                log.debug("Create Community : {}", comm);
//
//                // 성공 시 커뮤니티 리스트 화면으로 redirect
//                return "redirect:/comm/comm";
//            } catch (Exception e) {
//                // 실패 시 입력 form으로 forwarding
//                request.setAttribute("creationFailed", true);
//                request.setAttribute("exception", e);
//                request.setAttribute("comm", comm);
//
//                return "/comm/comm";
//            }
//
//        } else {
//            // userId가 null일 경우에 대한 처리 (예: 로그인 페이지로 리다이렉트 등)
//            log.debug("User ID is null in session. Redirecting to login page.");
//            return "redirect:/"; // 예시로 로그인 페이지로 리다이렉트
//        }
//    }
