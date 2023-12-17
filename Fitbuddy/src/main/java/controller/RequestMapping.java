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
        mappings.put("/user/loginform", new ForwardController("/user/loginForm.jsp")); //로그인 폼으로 이동
        mappings.put("/user/login", new LoginController()); //로그인 버튼을 클릭했을 때 연산 실행
        mappings.put("/user/logout", new LogoutController());
//        mappings.put("/user/list", new ListUserController());
//        mappings.put("/user/view", new ViewUserController());
        
        // 회원 가입 폼 요청과 가입 요청 처리 병합 (폼에 커뮤니티 선택 메뉴 추가를 위함)
//      mappings.put("/user/register/form", new ForwardController("/user/registerForm.jsp"));
//      mappings.put("/user/register", new RegisterUserController());
        mappings.put("/user/register", new RegisterUserController());

        // 사용자 정보 수정 폼 요청과 수정 요청 처리 병합
//      mappings.put("/user/update/form", new UpdateUserFormController());
//      mappings.put("/user/update", new UpdateUserController());        
//        mappings.put("/user/update", new UpdateUserController());
//        
//        mappings.put("/user/delete", new DeleteUserController());
        
        // 커뮤니티 관련 request URI 추가
        	mappings.put("/comm/comm", new ListCommunityController()); 
//        mappings.put("/community/list", new ListCommunityController());
//        mappings.put("/community/view", new ViewCommunityController());
//        mappings.put("/community/create/form", new ForwardController("/community/creationForm.jsp"));
        	mappings.put("/community/create", new CreateCommunityController());
//        mappings.put("/community/update", new UpdateCommunityController());
        
//        mappings.put("/mypage/mypage", new ListProfileController());
////        mappings.put("/mypage/profile", new ListProfileController());
////        mappings.put("/mypage/myMatching", new ListMatchingController());
////        mappings.put("/mypage/myPost", new ListPostController());
////        mappings.put("/mypage/myComment", new ListCommentController());
        
        mappings.put("/mypage/profile", new ProfileController());
        mappings.put("/mypage/myMatching", new MyMatchingController());
        mappings.put("/mypage/myPost", new MyPostController());
        mappings.put("/mypage/myComment", new MyCommentController());
        
        // todo 관련 request URI 추가
        //mappings.put("/todo", new ListTodoController());
        mappings.put("/todo", new CreateTodoController());
        mappings.put("/todo/todopost", new CreateTodoCommController());
        

        
        
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}