package pills.models;

public class LoginResponse {
	private String userName;
    private String userEmail;
    private String userContact;
    private Integer userRole;
    private Integer userId;
    
    public String getUserName(){
   	 return this.userName;
    }
    public void setUserName(String userName){
   	 this.userName = userName;
    }
    
    public String getUserEmail(){
   	 return this.userEmail;
    }
    public void setUserEmail(String userEmail){
   	 this.userEmail = userEmail;
    }
    
    public String getUserContact(){
   	 return this.userContact;
    }
    public void setUserContact(String userContact){
   	 this.userContact = userContact;
    }
    
    public Integer getUserRole(){
   	 return this.userRole;
    }
    public void setUserRole(Integer userRole){
   	 this.userRole = userRole;
    }
    public Integer getUserId(){
      	 return this.userId;
       }
       public void setUserId(Integer userId){
      	 this.userId = userId;
       }

}