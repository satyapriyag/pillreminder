package pills.entity;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * Pill Entity
 */

@Entity
@Table(name="pill"
    ,catalog="pillreminder" 
)
@SQLDelete(sql = "UPDATE pill SET deleted = '1' WHERE pill_id = ?")
//Filter added to retrieve only records that have not been soft deleted.
//@Where(clause = "deleted IS NULL")
public class Pill  implements java.io.Serializable {


     private Integer pillId;
     private Category category;
     private String pillName;
     private Character deleted;
    // private Set<Alarm> alarms = new HashSet<Alarm>(0);
//     private Set<Alternative> alternativesForPillId = new HashSet<Alternative>(0);
//     private Set<Alternative> alternativesForAlternatePillId = new HashSet<Alternative>(0);

    public Pill() {
    }
    public Pill(Integer pillId){
    	this.pillId = pillId;
    }
    /*public Pill( String pillName) {
        this.pillName = pillName;
    }*/
    public Pill(Category category, String pillName) {
        this.category = category;
        this.pillName = pillName;
    }
    public Pill(Category category, String pillName, Character deleted){//, Set<Alarm> alarms, Set<Alternative> alternativesForPillId, Set<Alternative> alternativesForAlternatePillId) {
       this.category = category;
       this.pillName = pillName;
       this.deleted = deleted;
//       this.alarms = alarms;
//       this.alternativesForPillId = alternativesForPillId;
//       this.alternativesForAlternatePillId = alternativesForAlternatePillId;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="pill_id", unique=true, nullable=false)
    public Integer getPillId() {
        return this.pillId;
    }
    
    public void setPillId(Integer pillId) {
        this.pillId = pillId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="pill_category_id", nullable=false)
    public Category getCategory() {
        return this.category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }

    
    @Column(name="pill_name", nullable=false, length=30)
    public String getPillName() {
        return this.pillName;
    }
    
    public void setPillName(String pillName) {
        this.pillName = pillName;
    }

    
    @Column(name="deleted", length=1)
    public Character getDeleted() {
        return this.deleted;
    }
    
    public void setDeleted(Character deleted) {
        this.deleted = deleted;
    }

//@OneToMany(fetch=FetchType.LAZY, mappedBy="pill")
//    public Set<Alarm> getAlarms() {
//        return this.alarms;
//    }
//    
//    public void setAlarms(Set<Alarm> alarms) {
//        this.alarms = alarms;
//    }

//@OneToMany(fetch=FetchType.LAZY, mappedBy="pillByPillId")
//    public Set<Alternative> getAlternativesForPillId() {
//        return this.alternativesForPillId;
//    }
//    
//    public void setAlternativesForPillId(Set<Alternative> alternativesForPillId) {
//        this.alternativesForPillId = alternativesForPillId;
//    }
//
//@OneToMany(fetch=FetchType.LAZY, mappedBy="pillByAlternatePillId")
//    public Set<Alternative> getAlternativesForAlternatePillId() {
//        return this.alternativesForAlternatePillId;
//    }
//    
//    public void setAlternativesForAlternatePillId(Set<Alternative> alternativesForAlternatePillId) {
//        this.alternativesForAlternatePillId = alternativesForAlternatePillId;
//    }




}


