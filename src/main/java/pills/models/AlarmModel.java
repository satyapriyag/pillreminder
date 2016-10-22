/**
 * 
 */
package pills.models;

import java.util.Date;

/**
 * Model to alarm entity
 * @author satya
 *
 */
public class AlarmModel {

    private Integer AId;
    private Integer APillId;
    private Integer AUserId;
    private Date AStartDate;
    private Date AEndDate;
    private int ARecurrence;
    
    public Integer getAId() {
        return this.AId;
    }
    
    public void setAId(Integer AId) {
        this.AId = AId;
    }
    public Integer getAPillId() {
        return this.APillId;
    }
    
    public void setAPillId(Integer pillId) {
        this.APillId = pillId;
    }
    public Integer getAUserId() {
        return this.AUserId;
    }
    
    public void setAUserId(Integer userId) {
        this.AUserId = userId;
    }
    
    public Date getAEndDate(){
    	return this.AEndDate;
    }
    
    public void setAEndDate(Date date){
    	this.AEndDate = date;
    }
	
    public Date getAStartDate(){
    	return this.AStartDate;
    }
    
    public void setAStartDate(Date date){
    	this.AStartDate = date;
    }
    
    public int getARecurrence(){
    	return this.ARecurrence;
    }
    
    public void setARecurrence(int aRecurrence){
    	this.ARecurrence = aRecurrence;
    }
}
