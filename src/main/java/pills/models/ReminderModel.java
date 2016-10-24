package pills.models;

public class ReminderModel{
	
	private String userMail;
	private String userName;
	private String pillName;
	
	public String getUserMail(){
		return this.userMail;
	}
	public void setUserMail(String userMail){
		this.userMail = userMail;
	}
	
	public String getUserName(){
		return this.userName;
	}
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public String getPillName(){
		return this.pillName;
	}
	public void setPillName(String pillName){
		this.pillName = pillName;
	}
}