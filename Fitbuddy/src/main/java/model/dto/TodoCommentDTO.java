package model.dto;

import java.util.Date;

public class TodoCommentDTO {
	private int todopostId;
	private String content;
	private int todoCommentId;
	private int userId;
	private int todoCheck;
	
	
	public TodoCommentDTO() {
    }
	
	public TodoCommentDTO(int todopostId, String content,int todoCommentId, int userId, int todoCheck) {
        this.userId = userId;
        this.content = content;
        this.todoCommentId = todoCommentId;
        this.todoCheck = todoCheck;
        this.todopostId = todopostId;
    }
	
	public TodoCommentDTO(int todopostId, String content, int userId, int todoCheck) {
        this.userId = userId;
        this.content = content;
        this.todoCheck = todoCheck;
        this.todopostId = todopostId;
    }
	
	
	
	public int getTodopostId() { return todopostId; }
	public void setTodopostId(int todopostId) { this.todopostId = todopostId; }
	
	public String getContent() { return content; }
	public void setContent(String content) { this.content = content; }
	
	public int getTodoCommentId() { return todoCommentId; }
	public void setTodoCommentId(int todoCommentId) { this.todoCommentId = todoCommentId; }
	
	public int getUserId() { return userId; }
	public void setUserId(int userId) { this.userId = userId; }
	
	public int getTodoCheck() { return todoCheck; }
	public void setTodoCheck(int todoCheck) { this.todoCheck = todoCheck; }
	

}
