package pills.models;

public class AddUserModel {

     private String userName;
     private String userEmail;
     private String userContact;
     private Integer userRole;
     
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
     
}