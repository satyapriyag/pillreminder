package pills.entity;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * Alarm Entity
 */

@Entity
@Table(name="alarm"
    ,catalog="pillreminder"
    //, uniqueConstraints = @UniqueConstraint(columnNames={"a_user_id", "a_pill_id"}) 
)
@SQLDelete(sql = "UPDATE alarm SET deleted = '1' WHERE a_id = ?")
//Filter added to retrieve only records that have not been soft deleted.
@Where(clause = "deleted IS NULL")
public class Alarm  implements java.io.Serializable {


     private Integer AId;
     private Pill pill;
     private User user;
     private Date AStartDate;
     private Date AEndDate;
     private int ARecurrence;
     private Character deleted;

    public Alarm() {
    }

	public Alarm(Integer aId){
		this.AId = aId;
	}
    public Alarm(Pill pill, User user, int ARecurrence) {
        this.pill = pill;
        this.user = user;
        this.ARecurrence = ARecurrence;
    }
    public Alarm(Pill pill, User user, Date AStartDate, Date AEndDate, int ARecurrence) {
       this.pill = pill;
       this.user = user;
       this.AStartDate = AStartDate;
       this.AEndDate = AEndDate;
       this.ARecurrence = ARecurrence;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="a_id", unique=true, nullable=false)
    public Integer getAId() {
        return this.AId;
    }
    
    public void setAId(Integer AId) {
        this.AId = AId;
    }

    @Column(name="deleted", length=1)
    public Character getDeleted() {
        return this.deleted;
    }
    
    public void setDeleted(Character deleted) {
        this.deleted = deleted;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="a_pill_id", nullable=false)
    public Pill getPill() {
        return this.pill;
    }
    
    public void setPill(Pill pill) {
        this.pill = pill;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="a_user_id", nullable=false)
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="a_start_date", length=10)
    public Date getAStartDate() {
        return this.AStartDate;
    }
    
    public void setAStartDate(Date AStartDate) {
        this.AStartDate = AStartDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="a_end_date", length=10)
    public Date getAEndDate() {
        return this.AEndDate;
    }
    
    public void setAEndDate(Date AEndDate) {
        this.AEndDate = AEndDate;
    }

    
    @Column(name="a_recurrence", nullable=false)
    public int getARecurrence() {
        return this.ARecurrence;
    }
    
    public void setARecurrence(int ARecurrence) {
        this.ARecurrence = ARecurrence;
    }




}


