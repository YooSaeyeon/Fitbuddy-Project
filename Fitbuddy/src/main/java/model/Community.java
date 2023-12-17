package model;

import java.util.Date;

/**
 * 커뮤티니 관리를 위해 필요한 도메인 클래스. Community 테이블과 대응됨
 */
public class Community {
    private int cmUserId;
    private int cmPostId;
    private String content;
//    private String img;
    private Date commDate;
    private String userProfile;
    private String userName;

    // Default constructor
    public Community() { }

    // Constructor with all parameters
    public Community(int cmUserId, int cmPostId, String content, Date commDate, String userProfile, String userName) {
        this.cmUserId = cmUserId;
        this.cmPostId = cmPostId;
        this.content = content;
//        this.img = img;
        this.commDate = commDate != null ? commDate : new Date();
        this.userProfile = userProfile;
        this.userName = userName;
    }

    // Constructor without cmPostId and commDate parameters (using default values)
    public Community(int cmUserId, String content,  String userProfile, String userName) {
        this(cmUserId, 0, content, new Date(), userProfile, userName);
    }

    // Getter and setter methods

    public int getCmUserId() {
        return cmUserId;
    }

    public void setCmUserId(int cmUserId) {
        this.cmUserId = cmUserId;
    }

    public int getCmPostId() {
        return cmPostId;
    }

    public void setCmPostId(int cmPostId) {
        this.cmPostId = cmPostId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

//    public String getImg() {
//        return img;
//    }
//
//    public void setImg(String img) {
//        this.img = img;
//    }

    public Date getCommDate() {
        return commDate;
    }

    public void setCommDate(Date commDate) {
        this.commDate = commDate;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Community [cmPostId=" + cmPostId + ", content=" + content + ",  commDate=" + commDate +
                ", userProfile=" + userProfile + ", userName=" + userName + "]";
    }
}