package model.dto;

import java.util.Date;
import java.text.SimpleDateFormat;

public class TodoDTO {
	private int userId;
	private Date createdAt;
	private int todopostId;
	
	public TodoDTO() {
    }
	
	public TodoDTO(int userId, Date createdAt, int todopostId) {
        this.userId = userId;
        this.createdAt = createdAt != null ? createdAt : new Date();
        this.todopostId = todopostId;
    }
	
	
	public String getFormattedCreatedAt() {
        // Format the date using SimpleDateFormat
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        return dateFormat.format(createdAt);
    }
	
	
	public int getUserId() { return userId; }
	public void setUserId(int userId) { this.userId = userId; }
	
	public Date getCreatedAt() { return createdAt; }
	public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
	
	public int getTodopostId() { return todopostId; }
	public void setTodopostId(int todopostId) { this.todopostId = todopostId; }

	@Override
    public String toString() {
        return "Todo [todopostId=" + todopostId + ", created_at=" + createdAt + ", userId=" + userId + " ]";
    }

}
