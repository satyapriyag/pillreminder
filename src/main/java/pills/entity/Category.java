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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * Category entity
 */

@Entity
@Table(name="category"
    ,catalog="pillreminder" 
)
@SQLDelete(sql = "UPDATE category SET deleted = '1' WHERE category_id = ?")
//Filter added to retrieve only records that have not been soft deleted.
@Where(clause = "deleted IS NULL")
public class Category  implements java.io.Serializable {


     private Integer categoryId;
     private String categoryName;
     private Character deleted;
     private Set<Pill> pills = new HashSet<Pill>(0);

     public Category() {
     }

 	public Category(Integer categoryId){
 		this.categoryId = categoryId;
 	}
     public Category(String categoryName) {
         this.categoryName = categoryName;
     }
     public Category(String categoryName, Set<Pill> pills) {
        this.categoryName = categoryName;
        this.pills = pills;
     }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="category_id", unique=true, nullable=false)
    public Integer getCategoryId() {
        return this.categoryId;
    }
    
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    
    @Column(name="category_name", nullable=false, length=30)
    public String getCategoryName() {
        return this.categoryName;
    }
    
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    
    @Column(name="deleted", length=1)
    public Character getDeleted() {
        return this.deleted;
    }
    
    public void setDeleted(Character deleted) {
        this.deleted = deleted;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="category")
    public Set<Pill> getPills() {
        return this.pills;
    }
    
    public void setPills(Set<Pill> pills) {
        this.pills = pills;
    }




}


