package pills.models;

public class AddPillModel {
	private String pillName;
	private Integer pillCategoryId;

	public String getPillName(){
		return pillName;
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