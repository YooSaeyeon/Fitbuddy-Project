package controller.user;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.User;
import model.dao.UserDAO;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;

public class UpdateUserController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


        // 전송된 요청 메시지의 타입이 multipart 인지 여부를 체크
        boolean check = ServletFileUpload.isMultipartContent(request);
        if (check) {
            ServletContext context = request.getServletContext();
            String path = context.getRealPath("/uploads");
            File dir = new File(path);

            if (!dir.exists()) dir.mkdir();

            int userId = 0;
			try {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                factory.setSizeThreshold(10 * 1024);
                factory.setRepository(dir);

                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setSizeMax(10 * 1024 * 1024);
                upload.setHeaderEncoding("utf-8");

                List<FileItem> items = (List<FileItem>) upload.parseRequest(request);

                for (FileItem item : items) {
                    if (!item.isFormField() && item.getFieldName().equals("profilePhoto")) {
                        String oriFilename = item.getName();
                        if (oriFilename == null || oriFilename.trim().length() == 0) continue;

                        String filename = UUID.randomUUID().toString() + "_" + oriFilename.substring(oriFilename.lastIndexOf("\\") + 1);

                        File file = new File(dir, filename);
                        item.write(file);

                        // 파일 업로드 후 userId에 해당하는 사용자 정보의 프로필 사진 업데이트 로직
                        
                        
//                     // 로그인한 사용자 식별
                        HttpSession session = request.getSession(false); // 현재 세션이 있는지 확인
                        if (session != null) {
                            User loggedInUser = (User) session.getAttribute("loggedInUser");
                            if (loggedInUser != null) {
                                userId = loggedInUser.getUserId(); // 현재 로그인한 사용자의 ID를 얻음
                                UserDAO userDAO = new UserDAO(); // UserDAO 인스턴스 생성
                                userDAO.updateProfilePhoto(userId, filename); // 사용자 정보에 프로필 사진 업데이트
                            }
                        }
                        
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            request.setAttribute("userId", userId);
        }

        return "redirect:/mypage/profile";

    }
}
