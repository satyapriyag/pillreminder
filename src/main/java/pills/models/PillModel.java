package pills.models;

public class PillModel {
	private Integer pillId;
	private String pillName;
	private Integer pillCategoryId;
	
	public Integer getPillId(){
		return pillId;
	}
	public String getPillName(){
		return pillName;
	}
	public void setPillId(Integer pillId){
		this.pillId = pillId;
	}
	public void setPillName(String pillName){
		this.pillName = pillName;
	}
	public Integer getPillCategoryId(){
		return pillCategoryId;
	}
	public void setPillCategoryId(Integer pillCategoryId){
		this.pillCategoryId = pillCategoryId;
	}
}
