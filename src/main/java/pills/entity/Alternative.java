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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * Alternative entity
 */

@Entity
@Table(name="alternative"
    ,catalog="pillreminder"
    , uniqueConstraints = @UniqueConstraint(columnNames={"pill_id", "alternate_pill_id"}) 
)
@SQLDelete(sql = "UPDATE alternative SET deleted = '1' WHERE alt_id = ?")
//Filter added to retrieve only records that have not been soft deleted.
@Where(clause = "deleted IS NULL")
public class Alternative  implements java.io.Serializable {


     private Integer altId;
     private Pill pillByPillId;
     private Pill pillByAlternatePillId;
     private Character deleted;

    public Alternative() {
    }

    public Alternative(Pill pillByPillId, Pill pillByAlternatePillId) {
       this.pillByPillId = pillByPillId;
       this.pillByAlternatePillId = pillByAlternatePillId;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="alt_id", unique=true, nullable=false)
    public Integer getAltId() {
        return this.altId;
    }
    
    public void setAltId(Integer altId) {
        this.altId = altId;
    }
    
    @Column(name="deleted", length=1)
    public Character getDeleted() {
        return this.deleted;
    }
    
    public void setDeleted(Character deleted) {
        this.deleted = deleted;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="pill_id", nullable=false)
    public Pill getPillByPillId() {
        return this.pillByPillId;
    }
    
    public void setPillByPillId(Pill pillByPillId) {
        this.pillByPillId = pillByPillId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="alternate_pill_id", nullable=false)
    public Pill getPillByAlternatePillId() {
        return this.pillByAlternatePillId;
    }
    
    public void setPillByAlternatePillId(Pill pillByAlternatePillId) {
        this.pillByAlternatePillId = pillByAlternatePillId;
    }

}


