package model;


public class Comment {
	private int cmCommentId;
    private int cmPostId;
    private int cmUserId;
    private String content;
    private String userProfile;
    private String userName;
    
    public Comment() { }

    public Comment(int cmCommentId, int cmPostId, int cmUserId, String content, String userProfile ,String userName) {
        this.cmCommentId = cmCommentId;
        this.cmPostId = cmPostId;
        this.cmUserId = cmUserId;
        this.content = content;
        this.userProfile = userProfile;
        this.userName=userName;
    }
    
    public int getCmUserId() {
        return cmUserId;
    }

    public void setCmUserId(int cmUserId) {
        this.cmUserId = cmUserId;
    }

    public int getCmCommentId() {
        return cmCommentId;
    }

    public void setCmCommentId(int cmCommentId) {
        this.cmCommentId = cmCommentId;
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
        return "Comment [cmCommentId="+cmCommentId+",cmPostId=" + cmPostId + ", content=" + content + 
                ", userProfile=" + userProfile +"userName="+userName+",cmUserId=" + cmUserId + "]";
    }
}