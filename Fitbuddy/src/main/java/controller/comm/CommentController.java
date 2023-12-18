
package controller.comm;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import controller.Controller;
import model.Comment;
import model.dao.CommentDao;

public class CommentController implements Controller {
	
	private static final Logger log = LoggerFactory.getLogger(CommentController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {

            int cmPostId = Integer.parseInt(request.getParameter("cmPostId"));


            HttpSession session = request.getSession();
            Object userIdObject = session.getAttribute("userId");
            int cmUserId = (userIdObject != null) ? Integer.parseInt(userIdObject.toString()) : 17;

            String userProfile = ""; 
            // Instantiate Comment and set its properties
            Comment comment = new Comment();
            comment.setCmPostId(cmPostId);
            comment.setCmUserId(cmUserId);
            comment.setContent(request.getParameter("content"));
            comment.setUserProfile(userProfile);

            CommentDao commentDao = new CommentDao();
            Comment createdComment = commentDao.create(comment);


            return "redirect:/community/view/" + cmPostId;

           
        } catch (Exception e) {
            e.printStackTrace();
            return "/error.jsp";
        }
    }
}
