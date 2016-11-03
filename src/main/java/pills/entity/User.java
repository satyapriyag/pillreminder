package pills.entity;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * User Entity
 */

@Entity
@Table(name="user"
    ,catalog="pillreminder"
)

@SQLDelete(sql = "UPDATE user SET deleted = '1' WHERE user_id = ?")
//Filter added to retrieve only records that have not been soft deleted.
@Where(clause = "deleted IS NULL")
public class User  implements java.io.Serializable {


     private Integer userId;
     private String userName;
     private String userEmail;
     private String userContact;
     private Character deleted;
     //private Role userRole;
     private Set<UserRole> userRoles = new HashSet<UserRole>(0);
//     private Set<Alarm> alarms = new HashSet<Alarm>(0);

    public User() {
    }

	public User(Integer userId){
		this.userId = userId;
	}
    public User(String userName, String userEmail) {
        this.userName = userName;
        this.userEmail = userEmail;
    }
    public User(String userName, String userEmail, String userContact, Set<UserRole> userRoles){//, Set<Alarm> alarms) {
       this.userName = userName;
       this.userEmail = userEmail;
       this.userContact = userContact;
       this.userRoles = userRoles;
//       this.alarms = alarms;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="user_id", unique=true, nullable=false)
    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    
    @Column(name="user_name", nullable=false, length=30)
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    
    @Column(name="user_email", nullable=false, length=30)
    public String getUserEmail() {
        return this.userEmail;
    }
    
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    
    @Column(name="user_contact", length=30)
    public String getUserContact() {
        return this.userContact;
    }
    
    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }
    
    @Column(name="deleted", length=1)
    public Character getDeleted() {
        return this.deleted;
    }
    
    public void setDeleted(Character deleted) {
        this.deleted = deleted;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<UserRole> getUserRoles() {
        return this.userRoles;
    }
    
    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

//@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
//    public Set<Alarm> getAlarms() {
//        return this.alarms;
//    }
//    
//    public void setAlarms(Set<Alarm> alarms) {
//        this.alarms = alarms;
//    }




}