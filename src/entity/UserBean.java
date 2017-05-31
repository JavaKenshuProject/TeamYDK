package entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserBean implements Serializable{

	private String user_id;
	private String password;
	private Timestamp update_date;

	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Timestamp getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Timestamp update_date) {
		this.update_date = update_date;
	}


}
