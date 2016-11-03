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

/**
 * Role Entity
 */

@Entity
@Table(name="role"
    ,catalog="pillreminder"
)
public class Role  implements java.io.Serializable {


     private Integer roleId;
     private String roleName;
     private Set<UserRole> userRoles = new HashSet<UserRole>(0);

    public Role() {
    }

	
    public Role(String roleName) {
        this.roleName = roleName;
    }
    public Role(String roleName, Set<UserRole> userRoles) {
       this.roleName = roleName;
       this.userRoles = userRoles;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="role_id", unique=true, nullable=false)
    public Integer getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    
    @Column(name="role_name", nullable=false, length=30)
    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="role")
    public Set<UserRole> getUserRoles() {
        return this.userRoles;
    }
    
    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }




}


