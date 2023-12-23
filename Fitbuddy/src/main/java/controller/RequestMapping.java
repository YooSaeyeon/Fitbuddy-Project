package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.user.*;
import controller.comm.*;
import controller.mypage.*;
import controller.todo.*;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 각 uri에 대응되는 controller 객체를 생성 및 저장
    	 mappings.put("/", new ForwardController("index.jsp"));
         mappings.put("/main/main", new ForwardController("/main/main.jsp"));
        mappings.put("/user/loginform", new ForwardController("/user/loginForm.jsp")); //로그인 폼으로 이동
        mappings.put("/user/login", new LoginController()); //로그인 버튼을 클릭했을 때 연산 실행
        mappings.put("/user/logout", new LogoutController());

        
        // 회원 가입 폼 요청과 가입 요청 처리 병합 (폼에 커뮤니티 선택 메뉴 추가를 위함)

        mappings.put("/user/register", new RegisterUserController());

        // 사용자 정보 수정 폼 요청과 수정 요청 처리 병합   
        mappings.put("/user/update", new UpdateUserController());

     // 커뮤니티 관련 request URI
    	mappings.put("/community/commList", new ListCommunityController()); 
    	mappings.put("/community/view/{CMPOSTID}", new ViewCommunityController());
    	mappings.put("/community/create", new CreateCommunityController());
    	mappings.put("/community/comment", new CommentController());

        mappings.put("/mypage/profile", new ProfileController());

        // todo 관련 request URI 추가
        mappings.put("/todo", new CreateTodoController());
        mappings.put("/todo/todolist", new ListTodoController());
        mappings.put("/todo/todolist/comm", new ViewTodoController());
        mappings.put("/todo/todolist/comm/comm", new CreateTodoCommController());
        

     
        
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {
        // 디버그 코드
        logger.debug("uri search debug code: {}", uri);

       
        Controller controller = mappings.get(uri);

        if (controller == null) {
            //post view 중괄호 읽어오기
            for (String key : mappings.keySet()) {
                if (isUri(uri, key)) {
                    controller = mappings.get(key);
                    break;
                }
            }
        }

        return controller;
    }

    // 게시글 디테일 조회할때, 게시글 postid 매칭용 
    private boolean isUri(String uri, String pattern) {
        String[] uriParts = uri.split("/");
        String[] patternParts = pattern.split("/");

        if (uriParts.length != patternParts.length) {
            return false;
        }

        for (int i = 0; i < uriParts.length; i++) {
            if (!patternParts[i].equals(uriParts[i]) && !patternParts[i].startsWith("{") && !patternParts[i].endsWith("}")) {
                return false;
            }
        }

        return true;
    }
}