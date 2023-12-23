package controller.comm;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Comment;
import model.Community;
import model.dao.CommDao;
import model.dao.CommentDao;
import model.service.UserManager;


public class ViewCommunityController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String uri = request.getRequestURI();

        String[] uriParts = uri.split("/");
        String postIdParam = uriParts[uriParts.length - 1]; 

        int postId = Integer.parseInt(postIdParam);
        UserManager manager = UserManager.getInstance();

        Community community = manager.findCommPostById(postId);
        
        request.setAttribute("community", community);

        /*댓글*/
        CommentDao commentDao = new CommentDao();
   
        List<Comment> commentList = commentDao.findCommentList(postId);
        request.setAttribute("commentList", commentList);
       
      
        return "/comm/commView.jsp";  
    }
}