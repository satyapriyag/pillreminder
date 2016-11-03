package pills.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * UserRole Entity
 */

@Entity
@Table(name="user_role"
    ,catalog="pillreminder"
)
public class UserRole  implements java.io.Serializable {


     private Integer urId;
     private Role role;
     private User user;

    public UserRole() {
    }

    public UserRole(Role role, User user) {
       this.role = role;
       this.user = user;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="ur_id", unique=true, nullable=false)
    public Integer getUrId() {
        return this.urId;
    }
    
    public void setUrId(Integer urId) {
        this.urId = urId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ur_role_id", nullable=false)
    public Role getRole() {
        return this.role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ur_user_id", nullable=false)
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }




}